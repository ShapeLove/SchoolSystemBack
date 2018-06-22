package com.shape.web.util;

import com.shape.web.dto.CustomUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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

    public static CustomUser getUserFromSession() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return (CustomUser)request.getSession().getAttribute(Contant.SESSION_USER_KEY);
    }
}
