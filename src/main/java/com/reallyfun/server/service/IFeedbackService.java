package com.reallyfun.server.service;

import com.reallyfun.server.entity.Feedback;

import java.util.List;

public interface IFeedbackService {
    /**
     * 提交反馈
     *
     * @param userId   提交人ID
     * @param gameId   游戏ID
     * @param category 反馈分类
     * @param content  反馈内容
     */
    void insert(Integer userId, Integer gameId, Integer category, String content);

    /**
     * 撤销反馈
     *
     * @param id     反馈ID
     * @param userId 当前用户ID
     */
    void delete(Integer id, Integer userId);

    /**
     * 处理反馈
     *
     * @param id            反馈ID
     * @param handlerId     处理人ID
     * @param handleComment 处理意见
     */
    void handle(Integer id, Integer handlerId, String handleComment);

    /**
     * 根据条件获取页面范围内反馈列表
     *
     * @param userId    用户ID（可空）
     * @param gameId    游戏ID（可空）
     * @param category  反馈分类（可空）
     * @param isHandled 是否被处理（可空）
     * @param pageSize  页面大小
     * @param pageNum   页码
     * @return 反馈列表
     */
    List<Feedback> findAllFeedbackOfPage(
            Integer userId,
            Integer gameId,
            Integer category,
            Boolean isHandled,
            Integer pageSize,
            Integer pageNum
    );
}
