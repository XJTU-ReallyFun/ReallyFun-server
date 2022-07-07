package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.Rating;

public interface IRatingMapper {

    /**
     * 插入数据
     *
     * @param rating 评分类
     * @return 影响行数
     */
    Integer insertRating(Rating rating);

    /**
     *判断用户是否对同一游戏进行评价
     *
     * @param gameId 游戏id
     * @param userId 用户id
     * @return 返回该用户是否评分过
     */
    Rating findByGameId(Integer gameId,Integer userId);
}
