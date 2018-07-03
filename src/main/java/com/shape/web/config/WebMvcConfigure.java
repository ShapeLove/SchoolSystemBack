package com.shape.web.config;

import com.shape.web.filter.CrosFilter;
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
     *
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
        registry.addInterceptor(new CrosFilter()).addPathPatterns("/**");
        registry.addInterceptor(new LoginFilter()).addPathPatterns("/**").excludePathPatterns("/login/**");
        super.addInterceptors(registry);
    }
}
