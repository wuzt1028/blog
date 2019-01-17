package com.wuzt.myblog.controller;

import com.github.pagehelper.PageInfo;
import com.wuzt.myblog.common.redis.RedisUtil;
import com.wuzt.myblog.model.User;
import com.wuzt.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wuzt
 * @Date: 2018/6/19 17:54
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping("/getAllUser")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        PageInfo<User> userPage = new PageInfo<User>();
        Object obj = redisUtil.get("userPage");
        if(obj == null) {
            userPage = userService.findAllUser(pageNum, pageSize);
            redisUtil.set("userPage", userPage);
        }else{
            userPage = (PageInfo<User>)obj;
        }
        return userPage;
    }

}
