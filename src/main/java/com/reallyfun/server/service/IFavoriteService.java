package com.reallyfun.server.service;

public interface IFavoriteService {
    /**
     * 收藏游戏
     *
     * @param gameId 游戏id
     * @param userId 用户id
     */
    void collect(Integer gameId, Integer userId);

    /**
     * 取消收藏游戏
     *
     * @param gameId 取消收藏的游戏
     * @param userId 取消收藏的用户
     */
    void delete(Integer gameId, Integer userId);
}
