package com.reallyfun.server.controller;

import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.IUserService;
import com.reallyfun.server.service.ex.FileToolException;
import com.reallyfun.server.service.ex.UserException;
import com.reallyfun.server.util.FileTool;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 处理⽤户相关请求的控制器类
 */
@RestController
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    /**
     * 用户注册
     *
     * @param user 需包含name, password, email
     * @return 响应结果
     */
    @PostMapping("/user/register")
    public ResponseResult<Void> register(@RequestBody User user) {
        // 用户注册
        userService.register(
                user.getName(),
                user.getPassword(),
                user.getEmail()
        );

        // 返回响应结果
        return ResponseResult.getResponseResult("注册成功！");
    }

    /**
     * 用户登录
     *
     * @param user    需包含name, password
     * @param session Http会话对象
     * @return 若登录成功，返回用户信息，否则返回相应错误信息
     */
    @PostMapping("/user/login")
    public ResponseResult<User> login(@RequestBody User user, HttpSession session) {
        // 检查重复登录
        if (getUserFromSession(session) != null) {
            throw new UserException("您已登录");
        }

        // 登录并获取当前用户数据
        User userResult = userService.login(user.getName(), user.getPassword());

        // 将用户数据存入HttpSession中
        setUserIntoSession(userResult, session);

        // 构造并返回响应结果
        User userRet = new User();
        userRet.setId(userResult.getId());
        userRet.setName(userResult.getName());
        userRet.setEmail(userResult.getEmail());
        userRet.setAvatar(userResult.getAvatar());
        userRet.setAuth(userResult.getAuth());
        return ResponseResult.getResponseResult("登录成功！", userRet);
    }

    /**
     * 头像文件大小的上限值（2MB）
     */
    public static final Integer AVATAR_MAX_SIZE = 2 * 1024 * 1024;

    /**
     * 允许上传的头像的文件类型
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    /**
     * 头像存储目录
     */
    @Value("${user.avatar.upload-dir}")
    private String avatarUploadDir;

    /**
     * 修改用户头像
     *
     * @param file    头像文件
     * @param session HttpSession对象
     * @return 新的头像文件名结果
     */
    @PostMapping("/user/avatar")
    public ResponseResult<String> uploadAvatar(
            @RequestParam("avatar") MultipartFile file,
            HttpSession session) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            throw new UserException("上传的头像文件不允许为空");
        }

        // 生成文件名
        String filename;
        try {
            filename = FileTool.randomFileName(file);
        } catch (FileToolException e) {
            throw new UserException("文件名后缀有误");
        }

        // 上传头像文件
        try {
            FileTool.uploadFile(
                    file,
                    avatarUploadDir,
                    filename,
                    AVATAR_MAX_SIZE,
                    AVATAR_TYPES
            );
        } catch (FileToolException e) {
            throw new UserException("头像修改失败");
        }

        // 删除旧头像
        User user = getUserFromSession(session);
        String oldFilename = userService.getAvatarById(user.getId());
        try {
            FileTool.deleteFile(avatarUploadDir, oldFilename);
        } catch (FileToolException e) {
        }

        // 更新用户头像文件名
        userService.updateAvatar(user.getId(), filename);

        // 返回新头像文件名
        return ResponseResult.getResponseResult("头像修改成功", filename);
    }

    /**
     * 修改用户密码
     *
     * @param oldPassword 原密码明文
     * @param newPassword 新密码明文
     * @param session     HttpSession对象
     * @return 响应结果
     */
    @PatchMapping("/user/password")
    public ResponseResult<Void> password(
            @RequestParam("old_password") String oldPassword,
            @RequestParam("new_password") String newPassword,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 修改密码
        userService.updatePassword(user.getId(), oldPassword, newPassword);

        // 返回响应结果
        return ResponseResult.getResponseResult("密码修改成功！");
    }

    /**
     * 修改用户名
     *
     * @param name    修改后的用户名
     * @param session HttpSession对象
     * @return 响应结果
     */
    @PatchMapping("/user/name")
    public ResponseResult<Void> name(
            @RequestParam String name,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 修改用户名
        userService.updateName(user.getId(), name);

        // 返回响应结果
        return ResponseResult.getResponseResult("用户名修改成功！");
    }

    /**
     * 修改用户权限
     *
     * @param userId    被修改权限的用户ID
     * @param authValue 目标权限值
     * @param session   HttpSession对象
     * @return 响应结果
     */
    @PatchMapping("/user/auth")
    public ResponseResult<Void> auth(
            @RequestParam("user_id") Integer userId,
            @RequestParam("auth_value") Integer authValue,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 修改权限
        userService.updateAuth(userId, authValue, user.getId(), user.getAuth());

        // 返回响应结果
        return ResponseResult.getResponseResult("用户权限修改成功！");
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/user/{id}")
    public ResponseResult<User> user(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        // TODO: 屏蔽password, salt等敏感字段
        return ResponseResult.getResponseResult(user);
    }
}
