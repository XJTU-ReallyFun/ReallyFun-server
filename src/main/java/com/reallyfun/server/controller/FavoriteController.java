package com.reallyfun.server.controller;

import com.reallyfun.server.entity.Favorite;
import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.IFavoriteService;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("favorite")
public class FavoriteController extends BaseController {
    @Autowired
    IFavoriteService iFavoriteService;

    /**
     * 收藏游戏
     *
     * @param gameId  收藏的游戏id
     * @param session HttpSession对象
     * @return 若收藏成功，返回信息；不成功返回对应错误原因
     */
    @PostMapping("")
    public ResponseResult<Void> collect(@RequestParam("game_id") Integer gameId, HttpSession session) {
        User user = getUserFromSession(session);
        iFavoriteService.collect(gameId, user.getId());
        return ResponseResult.getResponseResult("收藏成功");
    }

    /**
     * 取消收藏游戏
     *
     * @param gameId  取消收藏的游戏id
     * @param session HttpSession对象
     * @return 若取消成功，返回信息；不成功则赶回对应原因
     */
    @DeleteMapping("")
    public ResponseResult<Void> delete(@RequestParam("game_id") Integer gameId, HttpSession session) {
        User user = getUserFromSession(session);
        iFavoriteService.delete(gameId, user.getId());
        return ResponseResult.getResponseResult("已取消收藏");
    }
}
