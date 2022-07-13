package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.Favorite;

import java.util.List;

public interface IFavoriteMapper {
    /**
     * 添加收藏
     *
     * @param favorite 收藏游戏内容
     * @return 受影响行数
     */
    Integer insert(Favorite favorite);

    /**
     * 删除某用户的游戏收藏
     *
     * @param gameId 游戏ID
     * @param userId 用户ID
     * @return 受影响的行数
     */
    Integer deleteFavoriteByIds(Integer gameId, Integer userId);

    /**
     * 判断用户是否收藏了某游戏
     *
     * @param gameId 游戏ID
     * @param userId 用户ID
     * @return 用户是否收藏了该游戏
     */
    Integer existByIds(Integer gameId, Integer userId);

    /**
     * 获取范围内某用户的游戏收藏
     *
     * @param userId 用户ID
     * @param offset 偏移量
     * @param limit  记录条数
     * @return 该范围内用户的游戏收藏
     */
    List<Favorite> findAllFavoriteByUserIdOfRange(Integer userId, Integer offset, Integer limit);

    /**
     * 获取某游戏的收藏人数
     *
     * @param gameId 游戏ID
     * @return 该游戏的收藏人数
     */
    Integer countFavoriteByGameId(Integer gameId);
}
