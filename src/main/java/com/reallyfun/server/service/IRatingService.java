package com.reallyfun.server.service;

import com.reallyfun.server.entity.Rating;

public interface IRatingService {
    /**
     * @param gameid 游戏id
     * @param userid 用户id
     * @param value  评分
     */
    void evaluate(Integer gameid, Integer userid, Integer value);
}
