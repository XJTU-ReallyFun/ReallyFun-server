package com.reallyfun.server.service;

import com.reallyfun.server.entity.History;

import java.util.List;

public interface IHistoryService {
    /**
     * 触发游戏时长累加（1分钟）
     *
     * @param userId 用户ID
     * @param gameId 游戏ID
     */
    void trigger(Integer userId, Integer gameId);

    /**
     * 获取游戏历史列表
     *
     * @param userId   用户ID
     * @param pageSize 页面大小
     * @param pageNum  页码
     * @return 游戏历史列表
     */
    List<History> findAllHistoryOfPage(Integer userId, Integer pageSize, Integer pageNum);
}
