package com.wuzt.myblog.controller;

import com.wuzt.myblog.model.*;
import com.wuzt.myblog.redis.RedisUtil;
import com.wuzt.myblog.service.BlogService;
import com.wuzt.myblog.service.BlogTypeService;
import com.wuzt.myblog.service.TagsService;
import com.wuzt.myblog.service.UserService;
import com.wuzt.myblog.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author wuzt
 * @date 2018/7/7 0007下午 7:05
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @InitBinder("blogsDto")
    public void initBinderBlogsDto(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("blogsDto.");
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
     *
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    /*
     * Description:验证码
     * @auther: wuzt
     * @date: 2019/1/24 10:53
     */
    @RequestMapping({"/randomCode"})
    public void genericRandomCode(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "codeName", required = true) String codeName)
            throws IOException {
        response.setHeader("Cache-Control", "private,no-cache,no-store");
        response.setContentType("image/png");
        byte width = 85;
        byte height = 28;
        BufferedImage image = new BufferedImage(width, height, 2);
        Graphics2D g = image.createGraphics();
        g.setComposite(AlphaComposite.getInstance(3, 1.0F));
        Random random = new Random();
        g.setColor(new Color(249, 247, 243));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Microsoft YaHei", 0, 20));
        String sRand = "";

        for (int responseOutputStream = 0; responseOutputStream < 4; ++responseOutputStream) {
            String rand = String.valueOf(random.nextInt(10));
            sRand = sRand + rand;
            g.setColor(new Color(44, 85, 110));
            g.drawString(rand, 13 * responseOutputStream + 16, 23);
        }

        redisUtil.set("COMMON_RAND_CODE_" + codeName, sRand, 180);
        g.dispose();
        ServletOutputStream arg11 = response.getOutputStream();
        ImageIO.write(image, "png", arg11);
        arg11.close();
    }

    /**
     * Description:后台用户登录
     * @auther: wuzt
     * @date: 2019/1/24 10:54
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userLogin(HttpServletResponse response, @RequestParam("username") String userName,
                                         @RequestParam("password") String password, @RequestParam(value = "remberMe", defaultValue = "0") String remberMe,
                                         @RequestParam("codeName") String codeName, @RequestParam("verity") String verity) {
        Map<String, Object> resultMap = null;
        ResultUtil resultUtil = new ResultUtil();
        try {
            String codeNameDto = (String) redisUtil.get("COMMON_RAND_CODE_" + codeName);
            redisUtil.del("COMMON_RAND_CODE_" + codeName);
            if (ObjectUtils.isNotNull(verity) && verity.equals(codeNameDto)){
                User user = new User();
                user.setUsername(userName);
                User userDto = userService.selectUsersByUserName(user);
                if (userDto != null) {
                    String passwordMD5 = MD5Util.MD5(password);
                    if (passwordMD5.equals(userDto.getPassword())) {
                        //登录验证成功，存cookie
                        String uuidStr = UUIDGenerator.getUUIDStr();
                        if ("1".equals(remberMe)) {
                            CookieUtil.setCookie(response, "adminSid", uuidStr, 30);
                            redisUtil.set(uuidStr, userDto, 60 * 60 * 24 * 30);
                        } else {
                            CookieUtil.setCookie(response, "adminSid", uuidStr, 1);
                            redisUtil.set(uuidStr, userDto, 60 * 60 * 24 * 1);
                        }
                        logger.info("==========userId:" + userDto.getId() + "登陆成功==========");
                        resultMap = resultUtil.getJsonMap(true,null, "登陆成功");
                    }else {
                        resultMap = resultUtil.getJsonMap(false,null, "账号或密码错误");
                    }
                }else {
                    resultMap = resultUtil.getJsonMap(false,null, "账号或密码错误");
                }
            }else {
                resultMap = resultUtil.getJsonMap(false,null, "验证码不正确");
            }

        } catch (Exception e) {
            logger.error("UserController.userLogin", e);
            resultMap = resultUtil.getJsonMap(false, e, "登陆失败");
        }
        return resultMap;
    }

    /*
     * Description:后台主结构
     * @auther: wuzt
     * @date: 2019/1/22 14:14
     */
    @RequestMapping("/main")
    public String adminMain(HttpServletRequest request, HttpServletResponse response,
                            Model model){
        try {
            String uuidStr = CookieUtil.getCookie(request, "adminSid");
            User user = (User) redisUtil.get(uuidStr);
            model.addAttribute("adminUserName", user.getNickname());
        }catch (Exception e){
            logger.error("AdminController.adminMain", e);
            return "redirect:/admin/login";
        }
        return "adminMain";
    }
    @RequestMapping("/exit")
    @ResponseBody
    public Map<String, Object> exit(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> resultMap = null;
        ResultUtil resultUtil = new ResultUtil();

        try {
            String uuidStr = CookieUtil.getCookie(request, "adminSid");

            redisUtil.del(uuidStr);
            resultMap = resultUtil.getJsonMap(true, null, "退出成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap = resultUtil.getJsonMap(false, null, "退出失败，请稍后重试");
        }
        return resultMap;
    }

    /*
     * Description:后台主页
     * @auther: wuzt
     * @date: 2019/1/22 17:50
     */
    @RequestMapping("/index")
    public String adminIndex(HttpServletRequest request, HttpServletResponse response,
                             Model model){
        try {

        }catch (Exception e){
            logger.error("AdminController.adminIndex", e);
            return "redirect:/admin/login";
        }
        return "adminIndex";
    }


    @RequestMapping("/blogList")
    public String blogList(HttpServletRequest request, Model model,
                           @RequestParam(value = "typeId", required = false)Integer typeId,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "tagId", required = false) Integer tagId,
                           @RequestParam(value = "endTime", required = false)String endTime,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "status", required = false, defaultValue = "-1")Integer status,
                           @RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer currentPage,
                           @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        try{
            String uuidStr = CookieUtil.getCookie(request, "adminSid");
            User user = (User) redisUtil.get(uuidStr);
            //查找该用户的博客分类
            Integer userId = user.getId();
            BlogType blogType = new BlogType();
            blogType.setUserId(userId);
            List<BlogType> blogTypeList = blogTypeService.selectByCondition(blogType);
            model.addAttribute("blogTypeList", blogTypeList);
            //查找该用户的博客标签
            Tags tag = new Tags();
            tag.setUserId(userId);
            List<Tags> tagsList = tagsService.selectByCondition(tag);
            model.addAttribute("tagsList", tagsList);
            Map<String, Object> params = new HashMap<>();
            params.put("typeId", typeId);
            params.put("title", title);
            params.put("tagId", tagId);
            params.put("startTime", startTime);
            params.put("endTime", endTime);
            params.put("status", status);
            Integer index = (currentPage - 1) * pageSize;
            params.put("index", index);
            params.put("pageSize", pageSize);
            List<BlogEntity> blogEntities = blogService.selectBlogListPage(params);
            Integer blogCount = blogService.selectBlogCount(params);
            model.addAttribute("blogEntities", blogEntities);
            model.addAttribute("blogCount", blogCount);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("pageSize", pageSize);
        }catch (Exception e){
            logger.error("AdminController.blogList", e);
            return "redirect:/blog/index";
        }
        return "adminBlogList";
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public Map<String, Object> upadteStatus(Integer id, Integer status){
        Map<String, Object> resultMap = null;
        ResultUtil resultUtil = new ResultUtil();
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("id", id);
            param.put("status", status);
            blogService.updateBlogStatusById(param);
            if (status == 1){
                resultMap = resultUtil.getJsonMap(true,null, "发表成功");
            }else {
                resultMap = resultUtil.getJsonMap(true,null, "删除成功");
            }
        } catch (Exception e) {
            logger.error("AdminController.upadteStatus", e);
            resultMap = resultUtil.getJsonMap(false, null, "更新失败");
        }
        return resultMap;
    }
    /*
     * Description:写博客页
     * @auther: wuzt
     * @date: 2019/1/28 14:47
     */
    @RequestMapping("/toAddBlog")
    public String toAddBlog(HttpServletRequest request, Model model) {
        try {
            String uuidStr = CookieUtil.getCookie(request, "adminSid");
            User user = (User) redisUtil.get(uuidStr);
            model.addAttribute("userEntity", user);
            //查找该用户的博客分类
            Integer userId = user.getId();
            BlogType blogType = new BlogType();
            blogType.setUserId(userId);
            List<BlogType> blogTypeList = blogTypeService.selectByCondition(blogType);
            model.addAttribute("blogTypeList", blogTypeList);
            //查找该用户的博客标签
            Tags tag = new Tags();
            tag.setUserId(userId);
            List<Tags> tagsList = tagsService.selectByCondition(tag);
            model.addAttribute("tagsList", tagsList);
            return "blog_add";
        } catch (Exception e) {
            logger.error("AdminController.toAddBlog", e);
            return "redirect:/blog/index";
        }
    }

    /*
     * Description:验证type和tag重复性
     * @auther: wuzt
     * @date: 2019/1/28 16:37
     */
    @RequestMapping("/checkTypeAndTag")
    @ResponseBody
    public Map<String, Object> checkTypeAndTag(HttpServletRequest request, @RequestParam("sign") Integer sign,
                                               @RequestParam(value="typeName", required = false) String typeName,
                                               @RequestParam(value="tagName", required = false) String tagName) {
        Map<String, Object> resultMap = null;
        ResultUtil resultUtil = new ResultUtil();
        try {
            String uuidStr = CookieUtil.getCookie(request, "adminSid");
            User user = (User) redisUtil.get(uuidStr);
            if(sign == 1){//验证typeName是否已有
                Map<String, Object> param = new HashMap<>();
                param.put("typeName", typeName);
                param.put("userId", user.getId());
                Integer integer = blogTypeService.selectCountByName(param);
                if (integer > 0){
                    resultMap = resultUtil.getJsonMap(true, false, "博客类型重复");
                }else {
                    resultMap = resultUtil.getJsonMap(true, true, "博客类型无重复");
                }
            }else if (sign == 2){//验证tagName是否已有
                Map<String, Object> param = new HashMap<>();
                param.put("tagName", tagName);
                param.put("userId", user.getId());
                Integer integer = tagsService.selectCountByName(param);
                if (integer > 0){
                    resultMap = resultUtil.getJsonMap(true, false, "博客标签重复");
                }else {
                    resultMap = resultUtil.getJsonMap(true, true, "博客标签无重复");
                }
            }else {
                resultMap = resultUtil.getJsonMap(false, false, "参数错误");
            }
        } catch (Exception e) {
            logger.error("AdminController.checkTypeAndTag", e);
            resultMap = resultUtil.getJsonMap(false, null, "系统繁忙");
        }
        return resultMap;
    }


    @RequestMapping("/addBlog")
    public Map<String, Object> addBlog(@ModelAttribute BlogsDto blogsDto) {
        Map<String, Object> resultMap = null;
        ResultUtil resultUtil = new ResultUtil();
        try {
            blogService.insertBlog(blogsDto);
            //清除首页第一页博客列表的缓存
            redisUtil.del("indexPage_1");
            resultMap = resultUtil.getJsonMap(true, false, "添加成功");
        } catch (Exception e) {
            logger.error("AdminController.addBlog", e);
            resultMap = resultUtil.getJsonMap(false, null, "系统繁忙");
        }
        return resultMap;
    }
}
