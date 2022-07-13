package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.Comment;
import com.reallyfun.server.entity.Like;

import java.util.List;

/**
 * 处理评论操作的持久层接口
 */
public interface ICommentMapper {
    /**
     * 新增评论
     *
     * @param comment 评论对象
     * @return 受影响的行数
     */
    Integer insert(Comment comment);

    /**
     * 根据评论ID删除评论
     *
     * @param id 评论ID
     * @return 受影响的行数
     */
    Integer deleteById(Integer id);

    /**
     * 新增点赞
     *
     * @param like 点赞对象
     * @return 受影响的行数
     */
    Integer insertLike(Like like);

    /**
     * 取消点赞
     *
     * @param userId    用户ID
     * @param commentId 评论ID
     * @return 受影响的行数
     */
    Integer deleteLikeByIds(Integer userId, Integer commentId);

    /**
     * 更新评论的点赞数（缓存）
     *
     * @param id 评论ID
     * @return 受影响的行数
     */
    Integer updateLikeCountById(Integer id);

    /**
     * 更新评论的回复数（缓存）
     *
     * @param id 评论ID
     * @return 受影响的行数
     */
    Integer updateReplyCountById(Integer id);

    /**
     * 根据评论ID获取评论（基本）信息
     *
     * @param id 评论ID
     * @return 评论对象
     */
    Comment findById(Integer id);

    /**
     * 获取符合条件的范围内的评论列表
     *
     * @param key      排序关键字，可选"like", "moment"
     * @param order    排序方式，可选升序false, 降序true
     * @param userId   评论发表人ID
     * @param gameId   游戏ID
     * @param replyId  回复的评论ID
     * @param myUserId 我的用户ID，用于获取点赞信息
     * @param isRoot   是否为根节点评论
     * @param offset   偏移量
     * @param limit    记录数
     * @return 评论列表
     */
    List<Comment> findAllOfRange(
            String key,
            Boolean order,
            Integer userId,
            Integer gameId,
            Integer replyId,
            Integer myUserId,
            Boolean isRoot,
            Integer offset,
            Integer limit
    );

    /**
     * 查询符合条件的评论数量
     *
     * @param userId   评论发表人ID
     * @param gameId   游戏ID
     * @param replyId  回复的评论ID
     * @param myUserId 我的用户ID，用于获取点赞信息
     * @param isRoot   是否为根节点评论
     * @return 评论数量
     */
    Integer count(
            Integer userId,
            Integer gameId,
            Integer replyId,
            Integer myUserId,
            Boolean isRoot
    );

    /**
     * 根据ID查询是否存在点赞
     *
     * @param userId    用户ID
     * @param commentId 评论ID
     * @return 若存在则返回1，否则返回null
     */
    Integer existLikeByIds(Integer userId, Integer commentId);
}
