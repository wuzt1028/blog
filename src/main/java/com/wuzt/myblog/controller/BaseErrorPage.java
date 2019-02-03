package com.wuzt.myblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuzt
 * @date 2018/8/8 0008下午 6:18
 * @Description:
 */
@Controller
public class BaseErrorPage implements ErrorController {

    Logger logger = LoggerFactory.getLogger(BaseErrorPage.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //获取statusCode:401,404,500
        /*Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code")
        if(statusCode == 401){
            return "/401"
        }else if(statusCode == 404){
            return "/404"
        }else if(statusCode == 403){
            return "/403"
        }else{
            return "/500"
        }*/
        //同意跳到错误页面
        return "errors";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}