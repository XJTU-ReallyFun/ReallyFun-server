package com.reallyfun.server.controller;

import com.reallyfun.server.entity.Favorite;
import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.IFavoriteService;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 处理收藏相关请求的控制器类
 */
@RestController
public class FavoriteController extends BaseController {
    @Autowired
    IFavoriteService favoriteService;

    /**
     * 收藏游戏
     *
     * @param gameId  收藏的游戏ID
     * @param session HttpSession对象
     * @return 若收藏成功，返回信息；不成功返回对应错误原因
     */
    @PostMapping("/favorite")
    public ResponseResult<Void> collect(
            @RequestParam("game_id") Integer gameId,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 添加收藏
        favoriteService.collect(gameId, user.getId());

        // 返回响应结果
        return ResponseResult.getResponseResult("收藏成功！");
    }

    /**
     * 取消收藏游戏
     *
     * @param gameId  取消收藏的游戏ID
     * @param session HttpSession对象
     * @return 若取消成功，返回信息；不成功则赶回对应原因
     */
    @DeleteMapping("/favorite")
    public ResponseResult<Void> delete(
            @RequestParam("game_id") Integer gameId,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 删除收藏
        favoriteService.delete(gameId, user.getId());

        // 返回响应结果
        return ResponseResult.getResponseResult("已取消收藏");
    }

    /**
     * 获取某用户的游戏收藏
     *
     * @param userId   用户ID
     * @param pageSize 页面大小
     * @param pageNum  页码
     * @return 页面范围内该用户的收藏
     */
    @GetMapping("/favorites")
    public ResponseResult<List<Favorite>> findAllFavoriteByUserIdOfPage(
            @RequestParam("user_id") Integer userId,
            @RequestParam("page_size") Integer pageSize,
            @RequestParam("page_num") Integer pageNum
    ) {
        List<Favorite> result = favoriteService.findAllFavoriteByUserIdOfPage(userId, pageSize, pageNum);
        return ResponseResult.getResponseResult(result);
    }
}
