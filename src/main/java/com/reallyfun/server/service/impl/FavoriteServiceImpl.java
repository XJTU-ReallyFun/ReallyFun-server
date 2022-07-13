package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.Favorite;
import com.reallyfun.server.mapper.IFavoriteMapper;
import com.reallyfun.server.mapper.IGameMapper;
import com.reallyfun.server.mapper.IUserMapper;
import com.reallyfun.server.service.IFavoriteService;
import com.reallyfun.server.service.ex.FavoriteException;
import com.reallyfun.server.service.ex.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理收藏数据的业务层实现类
 */
@Service
public class FavoriteServiceImpl implements IFavoriteService {
    @Autowired(required = false)
    IFavoriteMapper favoriteMapper;

    @Autowired(required = false)
    IUserMapper userMapper;

    @Autowired(required = false)
    IGameMapper gameMapper;

    @Override
    public void collect(Integer gameId, Integer userId) {
        // 判断游戏是否存在
        if (gameMapper.findById(gameId) == null) {
            throw new TagException("游戏不存在");
        }

        // 判断是否收藏过该游戏
        if (favoriteMapper.existByIds(gameId, userId) != null) {
            throw new FavoriteException("您已经收藏过该游戏");
        }

        // 构造收藏数据
        Favorite favorite = new Favorite();
        favorite.setGameId(gameId);
        favorite.setUserId(userId);
        favorite.createBy(userId);

        // 添加收藏并判断是否成功
        Integer result = favoriteMapper.insert(favorite);
        if (result != 1) {
            throw new FavoriteException("添加收藏失败");
        }
    }

    @Override
    public void delete(Integer gameId, Integer userId) {
        // 判断游戏是否存在
        if (gameMapper.findById(gameId) == null) {
            throw new TagException("游戏不存在");
        }

        // 判断是否收藏过该游戏
        if (favoriteMapper.existByIds(gameId, userId) == null) {
            throw new FavoriteException("您尚未收藏该游戏");
        }

        // 删除收藏并判断是否成功
        Integer result = favoriteMapper.deleteFavoriteByIds(gameId, userId);
        if (result != 1) {
            throw new FavoriteException("取消收藏失败");
        }
    }

    @Override
    public List<Favorite> findAllFavoriteByUserIdOfPage(Integer userId, Integer pageSize, Integer pageNum) {
        // 判断用户是否存在
        if (userMapper.findUserById(userId) == null) {
            throw new FavoriteException("用户不存在");
        }

        // 限定查询范围
        Integer size = Math.min(Math.max(pageSize, 1), 100);
        Integer num = Math.max(pageNum, 1);

        // 查询并返回结果
        List<Favorite> result = favoriteMapper.findAllFavoriteByUserIdOfRange(
                userId,
                size * (num - 1),
                size
        );
        // TODO: 添加游戏详细数据
        return result;
    }
}
