package com.wuzt.myblog.interceptor;

import com.wuzt.myblog.model.AccessRecord;
import com.wuzt.myblog.redis.RedisUtil;
import com.wuzt.myblog.utils.IPUtil;
import com.wuzt.myblog.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: wuzt
 * @Date: 2019/1/30 9:26
 * @Description:
 */
@Component
public class LoggerInteceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;
    private String[] excludeUrls;
    private static final Logger logger = LoggerFactory.getLogger(LoggerInteceptor.class);

    /**
     * 进入controller层之前拦截请求
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String ip = IPUtil.getIpAddr(request);
        String path = request.getContextPath() + request.getServletPath();
        if (!ObjectUtils.isNull(this.excludeUrls)) {
            String[] buffer = this.excludeUrls;
            int enume = buffer.length;

            for (int key = 0; key < enume; ++key) {
                String value = buffer[key];
                if (path.contains(value)) {
                    return true;
                }
            }
        }

        StringBuffer arg9 = new StringBuffer("");

        String arg11;
        String[] arg12;
        for (Enumeration arg10 = request.getParameterNames(); arg10
                .hasMoreElements(); arg9.append(arg11).append("=")
                     .append(Arrays.toString(arg12))) {
            arg11 = (String) arg10.nextElement();
            arg12 = request.getParameterValues(arg11);
            if (arg9.toString().length() > 0) {
                arg9.append("  ");
            }
        }
        if(!"/error".equals(path)){
            this.setAccessRecordMap(ip, path);
        }
        logger.info("+++user_access_log,ip=" + ip + ",url=" + path
                + ",parameter=" + arg9);
        return true;
    }

    public void setAccessRecordMap(String ip, String url){
        AccessRecord accessRecord = new AccessRecord();
        accessRecord.setIp(ip);
        accessRecord.setUrl(url);
        accessRecord.setCreateTime(new Date());
        List<AccessRecord> accessRecordList = null;
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String currentDate = df.format(new Date());
        Object o = redisUtil.get("accessRecord_" + currentDate);
        if (ObjectUtils.isNull(o)){
            accessRecordList = new ArrayList<AccessRecord>();
            accessRecordList.add(accessRecord);
        }else{
            accessRecordList = (List<AccessRecord>) o;
            accessRecordList.add(accessRecord);
        }
        redisUtil.set("accessRecord_" + currentDate, accessRecordList, 60*60*24*2);
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
