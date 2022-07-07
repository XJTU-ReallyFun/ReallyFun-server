package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.Favorite;
import com.reallyfun.server.mapper.IFavoriteMapper;
import com.reallyfun.server.service.IFavoriteService;
import com.reallyfun.server.service.ex.FavoriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Service
public class FavoriteServiceImpl implements IFavoriteService {
    @Autowired
    IFavoriteMapper iFavoriteMapper;

    public void collect(Integer gameId, Integer userId) {
        if (iFavoriteMapper.findById(gameId, userId) != null) {
            throw new FavoriteException("对不起，您已经收藏该游戏");
        }
        Favorite favorite = new Favorite();
        favorite.setGameId(gameId);
        favorite.setUserId(userId);
        favorite.createBy(userId);
        Integer row = iFavoriteMapper.insertFavorite(favorite);
        if (row != 1) {
            throw new FavoriteException("出现错误，请您重试");
        }
    }

    public void delete(Integer gameId, Integer userId) {
        if (iFavoriteMapper.findById(gameId, userId) == null) {
            throw new FavoriteException("对不起，您已经取消收藏该游戏");
        }
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.createBy(userId);
        favorite.setGameId(gameId);
        Integer row = iFavoriteMapper.deleteFavorite(favorite);
        if (row != 1) {
            throw new FavoriteException("出现错误，请稍后重试");
        }
    }
}
