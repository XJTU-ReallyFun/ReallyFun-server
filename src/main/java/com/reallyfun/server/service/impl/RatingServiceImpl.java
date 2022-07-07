package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.Rating;
import com.reallyfun.server.entity.User;
import com.reallyfun.server.mapper.IRatingMapper;
import com.reallyfun.server.mapper.IUserMapper;
import com.reallyfun.server.service.IRatingService;
import com.reallyfun.server.service.IUserService;
import com.reallyfun.server.service.ex.RatingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RatingServiceImpl implements IRatingService {

    @Autowired
    private IRatingMapper iRatingMapper;

    public void evaluate(Integer gameid, Integer userid, Integer value) {
        if (value < 0 || value > 5) {
            throw new RatingException("对不起，请重新评分");
        }
        Rating rating = new Rating();
        rating.setGameId(gameid);
        rating.setUserId(userid);
        rating.setRating(value);
        if (iRatingMapper.findByGameId(gameid, userid) != null) {
            throw new RatingException("抱歉，您已对该游戏进行评价过");
        }
        rating.createBy(userid);
        Integer row = iRatingMapper.insertRating(rating);
        if (row != 1) {
            throw new RatingException("添加游戏评分出现未知错误，请重试");
        }
    }

}
