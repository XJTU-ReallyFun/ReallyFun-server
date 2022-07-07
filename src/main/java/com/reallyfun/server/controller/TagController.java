package com.reallyfun.server.controller;

import com.reallyfun.server.entity.RelGameTag;
import com.reallyfun.server.entity.Tag;
import com.reallyfun.server.service.ITagService;
import com.reallyfun.server.service.ex.InsertException;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 处理⽤户相关请求的控制器类 */
@RestController
@RequestMapping("tag")
public class TagController {
    @Autowired
    private ITagService tagService;
    @RequestMapping("insertTag")
    public ResponseResult<Void> insertTag(String content) {
        // 创建返回值
        ResponseResult<Void> response = new ResponseResult<>();
        try {
            // 调⽤业务对象执⾏注册
            tagService.insertTag(content);
            // 响应成功
            response.setMessage("添加成功");
        } catch (InsertException e) {
            // 插⼊数据异常
            response.setMessage("添加失败，请联系系统管理员");
        }
        return response;
    }
    @RequestMapping("deleteTag")
    public ResponseResult<Void> deleteTag(Integer id) {
        ResponseResult<Void> response = new ResponseResult<>();
        try {
            // 调⽤业务对象执⾏注册
            tagService.deleteTag(id);
            // 响应成功
            response.setMessage("删除成功");
        } catch (InsertException e) {
            // 插⼊数据异常
            response.setMessage("删除失败，请联系系统管理员");
        }
        return response;
    }

    @RequestMapping("bindGameTag")
    public ResponseResult<Void> bindGameTag(RelGameTag relgametag) {
        ResponseResult<Void> response = new ResponseResult<>();
        try {
            // 调⽤业务对象执⾏注册
            tagService.bindGameTag(relgametag);
            // 响应成功
            response.setMessage("绑定成功");
        } catch (InsertException e) {
            // 插⼊数据异常
            response.setMessage("绑定失败，请联系系统管理员");
        }
        return response;
    }

    @RequestMapping("unbindGameTag")
    public ResponseResult<Void> unbindGameTag(Integer gameId, Integer tagId) {
        ResponseResult<Void> response = new ResponseResult<>();
        try {
            // 调⽤业务对象执⾏注册
            tagService.unbindGameTag(gameId, tagId);
            // 响应成功
            response.setMessage("解除绑定成功");
        } catch (InsertException e) {
            // 插⼊数据异常
            response.setMessage("解除绑定失败，请联系系统管理员");
        }
        return response;
    }
}