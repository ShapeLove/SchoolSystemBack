package com.shape.web.config;

import com.shape.web.filter.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigure extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器
     * 拦截器的作用是拦截请求做一些操作 比如登录拦截 查看用户是否登录系统
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 登录拦截器
         * LoginFilter是自己实现的拦截器
         * addPathPatterns是表示所有的url地址都拦截
         * excludePathPatterns这里表示 除了带有/login/**的url地址不拦截 因为登录时不需要判断用户是否登录
         */
        registry.addInterceptor(new LoginFilter()).addPathPatterns("/**").excludePathPatterns("/login/**");
        super.addInterceptors(registry);
    }

    /**
     * 开启Cors跨域访问 为了能够实现前后端分离 前端一个项目后端一个项目 分工比较明确
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /**
         * addMapping表示拦截所有请求
         *
         */
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }
}
