package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.History;

import java.util.List;

public interface IHistoryMapper {
    /**
     * 增加新游戏记录
     *
     * @param history 历史记录对象
     * @return 影响行数
     */
    Integer insert(History history);

    /**
     * 更新游戏时长（累加一分钟）
     *
     * @param history 历史记录对象
     * @return 影响行数
     */
    Integer updateByIds(History history);

    /**
     * 根据用户和游戏ID查询历史记录
     *
     * @param userId 用户ID
     * @param gameId 游戏ID
     * @return 历史记录对象
     */
    History findByIds(Integer userId, Integer gameId);

    /**
     * 根据用户ID查询范围内历史记录列表
     *
     * @param userId 用户ID
     * @param offset 偏移量
     * @param limit  记录数
     * @return 该用户的历史记录列表
     */
    List<History> findAllByUserIdOfRange(Integer userId, Integer offset, Integer limit);
}
