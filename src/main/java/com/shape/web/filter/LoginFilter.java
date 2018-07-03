package com.shape.web.filter;

import com.shape.web.dto.CustomUser;
import com.shape.web.util.Contant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginFilter implements HandlerInterceptor {

    /**
     * preHandle表示在真正进入到Controller控制器方法前会调用 并且如果返回true才能够走正常的流程 否则页面报错
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /**
         * 1. 从session中获取key为user的CustomUser对象
         * 2. 如果有的话就代表已经登录过系统直接返回true 继续到Controller层中处理 否则返回notlogin表示未登录
         */
        CustomUser user = (CustomUser) httpServletRequest.getSession().getAttribute(Contant.SESSION_USER_KEY);
        if (Objects.nonNull(user)) {
            return true;
        }else {
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.getWriter().write("notlogin");
            httpServletResponse.getWriter().flush();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
