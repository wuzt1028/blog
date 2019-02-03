package com.wuzt.myblog.controller;

import com.wuzt.myblog.model.*;
import com.wuzt.myblog.redis.RedisUtil;
import com.wuzt.myblog.service.BlogService;
import com.wuzt.myblog.service.TagsService;
import com.wuzt.myblog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
public class BlogsController {

    @Value("${configValue.valine.appid}")
    private String appId;
    @Value("${configValue.valine.appKey}")
    private String appKey;

    private static final Logger logger = LoggerFactory.getLogger(BlogsController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagsService tagsService;

    @RequestMapping("/index")
    public String index2(){
        return "redirect:/";
    }

    @RequestMapping("/")
    public ModelAndView index(@RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer currentPage,
                              @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                              HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("index");
            Map<String, Object> params = new HashMap<String, Object>();
            Integer index = (currentPage - 1) * pageSize;
            params.put("index", index);
            params.put("pageSize", pageSize);
            List<BlogEntity> blogList = blogService.selectIndexPage(params);
            Integer blogCount = blogService.selectBlogCount(params);
            mv.addObject("blogList", blogList);
            mv.addObject("currentPage", currentPage);
            mv.addObject("pageSize", pageSize);
            mv.addObject("pageCount", (blogCount / pageSize) > 0 ? (blogCount / pageSize + 1) : 1);
            mv.addObject("blogCount", blogCount);
            User user = userService.selectUsersById(1);
            mv.addObject("user", user);
            Tags tag = new Tags();
            tag.setUserId(1);
            List<Tags> tags = tagsService.selectByCondition(tag);
            mv.addObject("tagList", tags);
            List<Blogs> clickDESCList = blogService.selectClickDESC();
            mv.addObject("clickDESCList", clickDESCList);
        } catch (Exception e) {
            logger.error("BlogsController.index", e);
            mv.setViewName("errors");
        }
        return mv;
    }

    @RequestMapping("/blog/list")
    public ModelAndView blogList(@RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer currentPage,
                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                 @RequestParam(value = "typeId", required = false) Integer typeId,
                                 @RequestParam(value = "tagName", required = false) String tagName,
                                 @RequestParam(value = "title", required = false) String title,
                                 HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("index");
            Map<String, Object> params = new HashMap<String, Object>();
            Integer index = (currentPage - 1) * pageSize;
            params.put("index", index);
            params.put("pageSize", pageSize);
            params.put("typeId", typeId);
            params.put("tagName", tagName);
            params.put("title", title);
            List<BlogEntity> blogList = blogService.selectBlogListPage(params);
            Integer blogCount = blogService.selectBlogCount(params);
            mv.addObject("blogList", blogList);
            mv.addObject("currentPage", currentPage);
            mv.addObject("pageSize", pageSize);
            mv.addObject("pageCount", (blogCount / pageSize) > 0 ? (blogCount / pageSize + 1) : 1);
            mv.addObject("blogCount", blogCount);
            User user = userService.selectUsersById(1);
            mv.addObject("user", user);
            Tags tag = new Tags();
            tag.setUserId(1);
            List<Tags> tags = tagsService.selectByCondition(tag);
            mv.addObject("tagList", tags);
            List<Blogs> clickDESCList = blogService.selectClickDESC();
            mv.addObject("clickDESCList", clickDESCList);
            mv.addObject("title",title);
        } catch (Exception e) {
            logger.error("BlogsController.blogList", e);
            mv.setViewName("errors");
        }
        return mv;
    }

    @RequestMapping("/blog/{id}")
    public ModelAndView blogInfo(@PathVariable("id") Integer id,
                                 HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("blogInfo");
            Object obj = redisUtil.get("clickNum_" + id);
            Integer clickNum = 0;
            if (obj != null) {
                clickNum = (Integer) obj;
            }
            clickNum += 1;
            BlogEntity blogEntity = blogService.selectBlogInfoById(id);
            blogEntity.setClickNum(blogEntity.getClickNum() + clickNum);
            if (clickNum > 10) {
                Blogs blog = new Blogs();
                blog.setId(id);
                blog.setClickNum(blogEntity.getClickNum());
                blogService.updateClickNumById(blog);
                clickNum = 0;
            }
            redisUtil.set("clickNum_" + id, clickNum);
            mv.addObject("blogEntity", blogEntity);
            User user = userService.selectUsersById(1);
            mv.addObject("user", user);
            mv.addObject("appId",appId);
            mv.addObject("appKey",appKey);
            Tags tag = new Tags();
            tag.setUserId(1);
            List<Tags> tags = tagsService.selectByCondition(tag);
            mv.addObject("tagList", tags);
            List<Blogs> clickDESCList = blogService.selectClickDESC();
            mv.addObject("clickDESCList", clickDESCList);
        } catch (Exception e) {
            logger.error("BlogsController.blogInfo", e);
            mv.setViewName("errors");
        }
        return mv;
    }
}
