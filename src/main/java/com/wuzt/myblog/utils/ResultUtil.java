package com.wuzt.myblog.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wuzt
 * @Date: 2019/1/22 11:18
 * @Description: 接口返回对象
 */
public class ResultUtil {

    public Map<String, Object> getJsonMap(boolean success, Object entity, String message) {
        HashMap json = new HashMap(4);
        json.put("success", Boolean.valueOf(success));
        json.put("message", message);
        json.put("entity", entity);
        return json;
    }

}
