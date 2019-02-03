package com.wuzt.myblog.interceptor;

import com.wuzt.myblog.model.User;
import com.wuzt.myblog.redis.RedisUtil;
import com.wuzt.myblog.utils.CookieUtil;
import com.wuzt.myblog.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: wuzt
 * @Date: 2019/1/22 14:22
 * @Description:后台拦截器
 */
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    private static final Logger log = LoggerFactory.getLogger(AdminLoginInterceptor.class);

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 开始进入请求地址拦截
        String uuidStr = CookieUtil.getCookie(httpServletRequest, "adminSid");
        User user = (User) redisUtil.get(uuidStr);
        if (user != null) {//判断用户是否登录
            //这里可以做权限验证
            log.info("=====ip:"+ IPUtil.getIpAddr(httpServletRequest)+"=====用户:"+user.getNickname()+"登录后台，请求"+httpServletRequest.getRequestURL()+"==========");

        }else{
            httpServletResponse.sendRedirect("/admin/login");// 未登录状态跳转到登录页面
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // 处理请求完成后视图渲染之前的处理操作
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // 视图渲染之后的操作
    }
}
