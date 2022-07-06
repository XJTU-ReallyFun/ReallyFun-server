package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.Favorite;

public interface IFavoriteMapper {
    /**
     * 收藏游戏
     *
     * @param favorite 收藏游戏内容
     * @return 受影响行数
     */
    Integer insertFavorite(Favorite favorite);

    /**
     * 通过id检查用户是否已经收藏过该游戏
     *
     * @param gameId 游戏id
     * @param userId 游戏id
     * @return 返回favorite对象，若为空，则可以进行收藏
     */
    Favorite findById(Integer gameId, Integer userId);

    /**
     * 删除收藏游戏
     *
     * @param favorite 收藏的对应游戏
     * @return 受影响行数
     */
    Integer deleteFavorite(Favorite favorite);
}
