package com.reallyfun.server.controller;

import com.reallyfun.server.entity.History;
import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.IHistoryService;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 处理历史记录相关请求的控制器类
 */
@RestController
public class HistoryController extends BaseController {
    @Autowired
    IHistoryService historyService;

    /**
     * 触发累加游戏时长（1分钟）
     *
     * @param gameId  游戏ID
     * @param session HttpSession对象
     * @return 响应结果
     */
    @PostMapping("/history")
    public ResponseResult<Void> history(
            @RequestParam("game_id") Integer gameId,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 累加游戏时长
        historyService.trigger(user.getId(), gameId);

        // 返回响应结果
        return ResponseResult.getResponseResult();
    }

    /**
     * 获取某用户的游戏历史列表
     *
     * @param pageSize 页面大小
     * @param pageNum  页码
     * @param session  HttpSession对象
     * @return 游戏历史列表
     */
    @GetMapping("/histories")
    public ResponseResult<List<History>> histories(
            @RequestParam("page_size") Integer pageSize,
            @RequestParam("page_num") Integer pageNum,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 获取历史记录列表
        List<History> result = historyService.findAllHistoryOfPage(
                user.getId(), pageSize, pageNum
        );

        // 返回响应结果
        return ResponseResult.getResponseResult(result);
    }
}
