package com.reallyfun.server.service;

public interface IRatingService {
    /**
     * 用户对游戏进行评分
     *
     * @param gameId 游戏ID
     * @param userId 用户ID
     * @param value  评分值
     */
    void rate(Integer gameId, Integer userId, Integer value);
}
