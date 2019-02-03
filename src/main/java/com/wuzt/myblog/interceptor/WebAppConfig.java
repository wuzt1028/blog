package com.wuzt.myblog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: wuzt
 * @Date: 2019/1/22 14:33
 * @Description:
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptorDto;
    @Autowired
    private LoggerInteceptor loggerInteceptorDto;


    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(adminLoginInterceptorDto).addPathPatterns("/admin/**").excludePathPatterns("/error","/admin/login","/admin/userLogin","/admin/randomCode");
        registry.addInterceptor(loggerInteceptorDto).addPathPatterns("/","/**/*").excludePathPatterns("/error","/admin/*","/static/**");
    }


}
