package com.reallyfun.server.service;

import com.reallyfun.server.entity.Comment;

import java.util.List;

public interface ICommentService {
    /**
     * 新增评论
     *
     * @param gameId  游戏ID
     * @param content 评论内容
     * @param replyId 要回复的评论ID
     * @param userId  发表人ID
     */
    void insert(Integer gameId, String content, Integer replyId, Integer userId);

    /**
     * 删除评论
     *
     * @param id     评论ID
     * @param userId 操作人ID
     */
    void delete(Integer id, Integer userId);

    /**
     * 为评论点赞
     *
     * @param id     评论ID
     * @param userId 点赞人ID
     */
    void like(Integer id, Integer userId);

    /**
     * 取消点赞
     *
     * @param id     评论ID
     * @param userId 操作人ID
     */
    void unlike(Integer id, Integer userId);

    /**
     * 获取评论列表
     *
     * @param key      排序关键字，可选"like", "moment"
     * @param order    排序方式，可选升序false, 降序true
     * @param userId   评论发表人ID
     * @param gameId   游戏ID
     * @param replyId  回复的评论ID
     * @param myUserId 我的用户ID，用于获取点赞信息
     * @param isRoot   是否为根节点评论
     * @param pageSize 页面大小
     * @param pageNum  页码
     * @return 符合条件的评论列表
     */
    List<Comment> findAllOfPage(
            String key,
            Boolean order,
            Integer userId,
            Integer gameId,
            Integer replyId,
            Integer myUserId,
            Boolean isRoot,
            Integer pageSize,
            Integer pageNum
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
}
