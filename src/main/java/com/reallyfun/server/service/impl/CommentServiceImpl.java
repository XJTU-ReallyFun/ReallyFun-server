package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.Comment;
import com.reallyfun.server.entity.Like;
import com.reallyfun.server.mapper.ICommentMapper;
import com.reallyfun.server.mapper.IGameMapper;
import com.reallyfun.server.service.ICommentService;
import com.reallyfun.server.service.ex.CommentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 处理评论数据的业务层实现类
 */
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired(required = false)
    ICommentMapper commentMapper;

    @Autowired(required = false)
    IGameMapper gameMapper;

    private static final Integer MAX_CONTENT_LENGTH = 400;

    @Override
    public void insert(Integer gameId, String content, Integer replyId, Integer userId) {
        // 判断游戏是否存在
        if (gameMapper.findById(gameId) == null) {
            throw new CommentException("游戏不存在");
        }

        // 判断评论内容字数是否合法
        if (content.isEmpty() || MAX_CONTENT_LENGTH < content.length()) {
            throw new CommentException("评论内容字数不符合要求");
        }

        // 构造评论数据
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setGameId(gameId);
        comment.setContent(content);
        comment.setMoment(new Date());
        comment.setLikeCount(0);
        comment.setReplyCount(0);

        // 获取被回复评论并设置comment相关属性
        if (replyId != null) {
            Comment reply = commentMapper.findById(replyId);
            if (reply == null) {
                throw new CommentException("要回复的评论不存在");
            }
            comment.setDirectReplyId(replyId);
            Integer root = reply.getRootReplyId();

            // 根节点的rootReplyId和directReplyId均为null
            if (root == null) {
                comment.setRootReplyId(replyId);
            } else {
                comment.setRootReplyId(root);
            }
        }

        // 设置创建日志字段
        comment.createBy(userId);

        // 插入并判断是否成功
        Integer result = commentMapper.insert(comment);
        if (result != 1) {
            throw new CommentException("评论失败");
        }

        // 更新根节点评论数
        Integer rootId = comment.getRootReplyId();
        if (rootId != null) {
            commentMapper.updateReplyCountById(rootId);
        }
    }

    @Override
    public void delete(Integer id, Integer userId) {
        // 判断评论是否存在
        Comment comment = commentMapper.findById(id);
        if (comment == null) {
            throw new CommentException("评论不存在");
        }

        // 判断是否有权限删除
        if (!comment.getUserId().equals(userId)) {
            throw new CommentException("您无权删除该评论");
        }

        // 删除并判断是否成功
        Integer result = commentMapper.deleteById(id);
        if (result != 1) {
            throw new CommentException("评论删除失败");
        }

        // 更新根节点评论数
        Integer root = comment.getRootReplyId();
        if (root != null) {
            commentMapper.updateReplyCountById(root);
        }
    }

    @Override
    public void like(Integer id, Integer userId) {
        // 判断评论是否存在
        Comment comment = commentMapper.findById(id);
        if (comment == null) {
            throw new CommentException("评论不存在");
        }

        // 判断是否已经点赞
        if (commentMapper.existLikeByIds(userId, id) != null) {
            throw new CommentException("您已点赞");
        }

        // 构造点赞数据
        Like like = new Like();
        like.setUserId(userId);
        like.setCommentId(id);
        like.createBy(userId);

        // 插入并判断是否成功
        Integer result = commentMapper.insertLike(like);
        if (result != 1) {
            throw new CommentException("点赞失败");
        }

        // 更新评论点赞数
        commentMapper.updateLikeCountById(id);
    }

    @Override
    public void unlike(Integer id, Integer userId) {
        // 判断是否还没点赞
        if (commentMapper.existLikeByIds(userId, id) == null) {
            throw new CommentException("您尚未点赞");
        }

        // 删除并判断是否成功
        Integer result = commentMapper.deleteLikeByIds(userId, id);
        if (result != 1) {
            throw new CommentException("取消点赞失败");
        }

        // 更新评论点赞数
        commentMapper.updateLikeCountById(id);
    }

    @Override
    public List<Comment> findAllOfPage(
            String key,
            Boolean order,
            Integer userId,
            Integer gameId,
            Integer replyId,
            Integer myUserId,
            Boolean isRoot,
            Integer pageSize,
            Integer pageNum
    ) {
        // 限定查询范围
        Integer size = Math.min(Math.max(pageSize, 1), 100);
        Integer num = Math.max(pageNum, 1);

        // 查询并返回结果
        List<Comment> result = commentMapper.findAllOfRange(
                key, order, userId, gameId, replyId, myUserId, isRoot,
                size * (num - 1), size
        );
        return result;
    }

    @Override
    public Integer count(
            Integer userId,
            Integer gameId,
            Integer replyId,
            Integer myUserId,
            Boolean isRoot
    ) {
        // 查询并返回结果
        Integer result = commentMapper.count(
                userId, gameId, replyId, myUserId, isRoot
        );
        return result;
    }
}
