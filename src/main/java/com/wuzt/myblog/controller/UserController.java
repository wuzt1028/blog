package com.wuzt.myblog.controller;

import com.alibaba.fastjson.JSON;
import com.wuzt.myblog.common.redis.RedisUtil;
import com.wuzt.myblog.model.*;
import com.wuzt.myblog.service.BlogService;
import com.wuzt.myblog.service.BlogTypeService;
import com.wuzt.myblog.service.TagsService;
import com.wuzt.myblog.service.UserService;
import com.wuzt.myblog.utils.CookieUtil;
import com.wuzt.myblog.utils.MD5Util;
import com.wuzt.myblog.utils.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuzt
 * @date 2018/7/7 0007下午 7:05
 * @Description:
 */
@Controller
@RequestMapping("/blog")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @InitBinder("blogsDto")
    public void initBinderBlogsDto(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("blogsDto.");
    }

    public Map<String, Object> getJsonMap(boolean success, String message,
                                          Object entity) {
        HashMap json = new HashMap(4);
        json.put("success", Boolean.valueOf(success));
        json.put("message", message);
        json.put("entity", entity);
        return json;
    }

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private BlogTypeService blogTypeService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private BlogService blogService;

    /**
     * 登录页
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

    /**
     * 用户登录验证
     * @param userName
     * @param password
     * @param remberMe
     * @return
     */
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public String userLogin(HttpServletResponse response, @RequestParam("username")String userName , @RequestParam("password")String password ,
                            @RequestParam(value = "remberMe", defaultValue = "0")String remberMe){
        try {
            User user = new User();
            user.setUsername(userName);
            User userDto = userService.selectUsersByUserName(user);
            if (userDto != null){
                String passwordMD5 = MD5Util.MD5(password);
                if(passwordMD5.equals(userDto.getPassword())){
                    //登录验证成功，存cookie
                    String uuidStr = UUIDGenerator.getUUIDStr();
                    if("1".equals(remberMe)){
                        CookieUtil.setCookie(response,"sid",uuidStr,30);
                        redisUtil.set(uuidStr,userDto,60*60*24*30);
                    }else{
                        CookieUtil.setCookie(response,"sid",uuidStr,1);
                        redisUtil.set(uuidStr,userDto,60*60*24*1);
                    }
                    logger.info("==========userId:"+userDto.getId()+"登陆成功==========");
                    return "redirect:/blog/toAddBlog";
                }
            }
            return "redirect:/blog/index";
        }catch (Exception e){
            logger.error("UserController.userLogin",e);
            return "redirect:/blog/index";
        }
    }

    @RequestMapping("/toAddBlog")
    public String toAddBlog(HttpServletRequest request,HttpServletResponse response,Model model){
        try{
            String uuidStr = CookieUtil.getCookie(request,"sid");
            User user = (User) redisUtil.get(uuidStr);
            if(user!=null){//判断用户是否登录

                model.addAttribute("userEntity",user);
                //查找该用户的博客分类
                Integer userId = user.getId();
                BlogType blogType = new BlogType();
                blogType.setUserId(userId);
                List<BlogType> blogTypeList = blogTypeService.selectByCondition(blogType);
                model.addAttribute("blogTypeList",blogTypeList);
                //查找该用户的博客标签
                Tags tag = new Tags();
                tag.setUserId(userId);
                List<Tags> tagsList = tagsService.selectByCondition(tag);
                model.addAttribute("tagsList",tagsList);
                return "blog_add";
            }else{
                return "redirect:/blog/index";
            }
        }catch (Exception e){
            logger.error("UserController.toAddBlog",e);
            return "redirect:/blog/index";
        }
    }

}
