package com.shape.web.util;

import com.shape.web.dto.CustomUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 存储Session上下文数据类
 */
public class WebContext {

    /**
     * 从当前session中获取数据
     * @param key
     * @return
     */
    public static Object getValueFromSession(String key) {
       HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
       return request.getSession().getAttribute(key);
    }

    /**
     * 从当前Session会话中获取登录的用户信息
     * @return
     */
    public static CustomUser getUserFromSession() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return (CustomUser)request.getSession().getAttribute(Contant.SESSION_USER_KEY);
    }
}
