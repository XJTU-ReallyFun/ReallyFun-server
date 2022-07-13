package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.Rating;

public interface IRatingMapper {

    /**
     * 插入评分数据
     *
     * @param rating 评分实体对象
     * @return 受到影响的行数
     */
    Integer insert(Rating rating);

    /**
     * 判断用户是否对游戏进行过评分
     *
     * @param gameId 游戏ID
     * @param userId 用户ID
     * @return 若过评分则返回1，否则返回null
     */
    Integer existByIds(Integer gameId, Integer userId);
}
