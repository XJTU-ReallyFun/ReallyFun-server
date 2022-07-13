package com.reallyfun.server.service;

import com.reallyfun.server.entity.Favorite;

import java.util.List;

public interface IFavoriteService {
    /**
     * 收藏游戏
     *
     * @param gameId 游戏ID
     * @param userId 用户ID
     */
    void collect(Integer gameId, Integer userId);

    /**
     * 取消收藏游戏
     *
     * @param gameId 取消收藏的游戏ID
     * @param userId 取消收藏的用户ID
     */
    void delete(Integer gameId, Integer userId);

    /**
     * 获取某用户的游戏收藏
     *
     * @param userId   用户ID
     * @param pageSize 每页的记录数
     * @param pageNum  页码
     * @return 页面范围内该用户的游戏收藏
     */
    List<Favorite> findAllFavoriteByUserIdOfPage(Integer userId, Integer pageSize, Integer pageNum);
}
