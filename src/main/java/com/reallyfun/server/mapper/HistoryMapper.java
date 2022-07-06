package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.History;

public interface HistoryMapper {
    /*
        *增加新游戏记录
        * @param history
        * @return 影响行数
     */
    Integer insert(History history);
    /*
        *根据用户名查询历史记录
        * @param userid
        * @return 匹配到的数据
     */
    History findByUserid(Integer user_id);
    /*
     *更新游戏时长
     * @param history
     * @return 影响行数
     */
    Integer update(History history);
}
