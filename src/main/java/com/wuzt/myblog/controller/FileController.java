package com.wuzt.myblog.controller;

import com.alibaba.fastjson.JSON;
import com.wuzt.myblog.model.FileEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wuzt
 * @Date: 2018/7/19 10:29
 * @Description:
 */
@Controller
@RequestMapping("/admin/file")
public class FileController {

    @Value("${configValue.fileUploadApi}")
    private String fileUploadApi;
    @Value("${configValue.fileHost}")
    private String fileHost;

    @RequestMapping("/uploadImgFile")
    public void uploadImgFile(HttpServletRequest request, HttpServletResponse response){
        //ImageUploadUtil.ckeditor(request, response, filePath);
        final String remote_url = fileUploadApi;// 第三方服务器请求地址
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            MultipartFile file = null;
            while (iter.hasNext()) {
                // 记录上传过程起始时的时间，用来计算上传时间
                // int pre = (int) System.currentTimeMillis();
                // 取得上传文件
                file = multiRequest.getFile(iter.next());
            }
            String fileName = file.getOriginalFilename();
            HttpPost httpPost = new HttpPost(remote_url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            builder.addTextBody("filename", fileName);// 类似浏览器表单提交，对应input的name和value
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse responseDto = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = responseDto.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
                Map resultMap = JSON.parseObject(result);
                String entityStr = resultMap.get("entity").toString();
                if(!"".equals(entityStr)){
                    System.out.println(entityStr);
                    List<FileEntity> returnList = JSON.parseArray(entityStr, FileEntity.class);
                    String fileUrl = returnList.get(0).getFileUrl();
                    // 结合ckeditor功能
                    // imageContextPath为图片在服务器地址，如upload/123.jpg,非绝对路径
                    String imageContextPath = fileHost + fileUrl;
                    response.setContentType("text/html;charset=UTF-8");
                    String callback = request.getParameter("CKEditorFuncNum");
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");
                    out.println("</script>");
                    out.flush();
                    out.close();
                }
                System.out.print(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}