package com.shape.web.service;

import com.shape.entity.User;
import com.shape.query.UserQuery;
import com.shape.web.dto.JsonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户逻辑接口
 */
public interface UserService {
    /**
     * 登录
     * @param
     * @return
     */
    JsonResult login(UserQuery userQuery, HttpServletRequest request);

    /**
     * 登出
     * @return
     */
    JsonResult loginOut(HttpServletRequest request);

    JsonResult resetPassword(User user);
}
