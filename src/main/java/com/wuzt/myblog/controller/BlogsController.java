package com.wuzt.myblog.controller;

import com.alibaba.fastjson.JSON;
import com.wuzt.myblog.common.redis.RedisUtil;
import com.wuzt.myblog.model.BlogEntity;
import com.wuzt.myblog.model.Blogs;
import com.wuzt.myblog.model.BlogsDto;
import com.wuzt.myblog.model.User;
import com.wuzt.myblog.service.BlogService;
import com.wuzt.myblog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuzt
 * @date 2018/8/8 0008下午 7:51
 * @Description:
 */
@Controller
@RequestMapping("/blog")
public class BlogsController {

    private static final Logger logger = LoggerFactory.getLogger(BlogsController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;


    @RequestMapping("/addBlog")
    public ModelAndView addBlog(@ModelAttribute BlogsDto blogsDto){
        ModelAndView modelAndView = new ModelAndView();
        try{
            String blogStr = JSON.toJSONString(blogsDto);
            System.out.println(blogStr);
            blogService.insertBlog(blogsDto);
            //清除首页第一页博客列表的缓存
            redisUtil.del("blogIndex");
            modelAndView.setViewName("addBlog_success");
            return modelAndView;
        }catch(Exception e){
            logger.error("BlogsController.addBlog",e);
            return modelAndView;
        }
    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "currentPage",defaultValue = "1",required = false)Integer currentPage,
                              @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
                              HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        try{
            mv.setViewName("index");
            Map<String,Object> params = new HashMap<String,Object>();
            Integer index = (currentPage - 1) * pageSize;
            params.put("index",index);
            params.put("pageSize",pageSize);
            List<BlogEntity> blogList = blogService.selectBlogListPage(params);
            Integer blogCount = blogService.selectBlogCount();
            mv.addObject("blogList",blogList);
            mv.addObject("currentPage",currentPage);
            mv.addObject("pageSize",pageSize);
            mv.addObject("pageCount",(blogCount/pageSize)>0?(blogCount/pageSize+1):1);
            mv.addObject("blogCount",blogCount);
            User user = userService.selectUsersById(1);
            mv.addObject("user",user);
        }catch(Exception e){
            logger.error("BlogsController.index",e);
            mv.setViewName("errors");
        }
        return mv;
    }

    @RequestMapping("/bloginfo/{id}")
    public ModelAndView blogInfo(@PathVariable("id") Integer id,
                              HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        try{
            mv.setViewName("blogInfo");
            Object obj = redisUtil.get("clickNum_"+id);
            Integer clickNum = 0;
            if(obj != null){
                clickNum = (Integer) obj;
            }
            clickNum += 1;
            BlogEntity blogEntity = blogService.selectBlogInfoById(id);
            blogEntity.setClickNum(blogEntity.getClickNum()+clickNum);
            if(clickNum > 10){
                Blogs blog = new Blogs();
                blog.setId(id);
                blog.setClickNum(blogEntity.getClickNum());
                blogService.updateClickNumById(blog);
                clickNum = 0;
            }
            redisUtil.set("clickNum_"+id, clickNum);
            mv.addObject("blogEntity",blogEntity);
            User user = userService.selectUsersById(1);
            mv.addObject("user",user);
        }catch(Exception e){
            logger.error("BlogsController.blogInfo",e);
            mv.setViewName("errors");
        }
        return mv;
    }
}
