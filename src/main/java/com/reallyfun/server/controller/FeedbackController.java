package com.reallyfun.server.controller;

import com.reallyfun.server.entity.Feedback;
import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.IFeedbackService;
import com.reallyfun.server.service.ex.FeedbackException;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 处理反馈相关请求的控制器类
 */
@RestController
public class FeedbackController extends BaseController {
    @Autowired
    private IFeedbackService feedbackService;

    /**
     * 提交反馈
     *
     * @param feedback 反馈实体对象
     * @param session  HttpSession 对象
     * @return 响应结果
     */
    @PostMapping("/feedback")
    public ResponseResult<Void> submit(@RequestBody Feedback feedback, HttpSession session) {
        // 获取当前用户对象
        User user = getUserFromSession(session);

        // 插入反馈数据
        feedbackService.insert(
                user.getId(),
                feedback.getGameId(),
                feedback.getCategory(),
                feedback.getContent()
        );

        // 返回响应结果
        return ResponseResult.getResponseResult("反馈成功！");
    }

    /**
     * 撤销反馈
     *
     * @param id      反馈ID
     * @param session HttpSession对象
     * @return 响应结果
     */
    @DeleteMapping("/feedback/{id}")
    public ResponseResult<Void> cancel(@PathVariable Integer id, HttpSession session) {
        // 获取当前用户对象
        User user = getUserFromSession(session);

        // 尝试删除反馈数据
        feedbackService.delete(id, user.getId());

        // 返回响应结果
        return ResponseResult.getResponseResult("反馈撤销成功！");
    }

    /**
     * 处理反馈
     *
     * @param feedback 反馈对象（需填写id, handleComment字段）
     * @param session  HttpSession 对象
     * @return 响应结果
     */
    @PostMapping("/feedback/handle")
    public ResponseResult<Void> handle(@RequestBody Feedback feedback, HttpSession session) {
        // 获取当前用户对象
        User user = getUserFromSession(session);

        // 处理反馈
        feedbackService.handle(feedback.getId(), user.getId(), feedback.getHandleComment());

        // 返回响应结果
        return ResponseResult.getResponseResult("反馈处理成功！");
    }

    /**
     * 根据筛选条件获取反馈列表
     *
     * @param userId    用户ID（可空）
     * @param gameId    游戏ID（可空）
     * @param category  反馈分类（可空）
     * @param isHandled 反馈是否被处理（可空）
     * @param pageSize  页面大小
     * @param pageNum   页码
     * @param session   HttpSession对象
     * @return 反馈列表
     */
    @GetMapping("/feedbacks")
    public ResponseResult<List<Feedback>> query(
            @RequestParam(value = "user_id", required = false) Integer userId,
            @RequestParam(value = "game_id", required = false) Integer gameId,
            @RequestParam(value = "category", required = false) Integer category,
            @RequestParam(value = "is_handled", required = false) Boolean isHandled,
            @RequestParam(value = "page_size") Integer pageSize,
            @RequestParam(value = "page_num") Integer pageNum,
            HttpSession session
    ) {
        // 获取当前用户对象
        User user = getUserFromSession(session);

        // 若当前为普通用户且筛选条件userId不等于自己的ID，则无权查询
        if (user.getAuth().equals(0) && !user.getId().equals(userId)) {
            throw new FeedbackException("您无权查询该筛选条件下的反馈");
        }

        // 获取反馈列表并返回
        List<Feedback> result = feedbackService.findAllFeedbackOfPage(
                userId, gameId, category, isHandled, pageSize, pageNum
        );
        return ResponseResult.getResponseResult(result);
    }
}
