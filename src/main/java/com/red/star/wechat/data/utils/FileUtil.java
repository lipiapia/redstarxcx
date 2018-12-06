package com.red.star.wechat.data.utils;

import com.alibaba.fastjson.JSONObject;
import com.red.star.wechat.data.entity.SysParam;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

public class FileUtil {

    /**
     * 获取文件上传token
     *
     * @param empNo
     * @return
     */
    public static String fetchToken(String empNo) {
        String url = SysParam.MACALLIEN_FILE_TOKEN_URL + empNo;
        Request request = Request.Get(url);
        String resp = HttpUtil.fetch(request);
        JSONObject object = JSONObject.parseObject(resp);
        if(object!=null && object.get("code")!= null && (Integer)object.get("code")==200) {
            return object.get("message").toString();
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param fileName    文件名
     * @param contentType
     * @param file        文件字节数组
     * @return 文件url
     */
    public static String uploadFile(String fileName, String contentType, byte[] file) {
        String token = fetchToken(SysParam.MACALLINE_FILE_EMPLOYEE_ID);
        HttpEntity entity = MultipartEntityBuilder.create()
                .addTextBody("appName", "weixin")
                .addTextBody("token", token)
                .addBinaryBody("file", file, ContentType.parse(contentType), fileName)
                .build();
        Request request = Request.Post(SysParam.MACALLINE_FILE_UPLOAD_URL).body(entity);
        String resp = HttpUtil.fetch(request);
        JSONObject object = JSONObject.parseObject(resp);
        if (object != null && object.get("code") != null && (Integer) object.get("code") == 200) {
            String value = object.get("value").toString();
            return JSONObject.parseObject(value).get("fileUrl").toString();
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param fileName 文件名
     * @param file     文件字节数组
     * @return 文件url
     */
    public static String uploadFile(String fileName, byte[] file) {
        return uploadFile(fileName, ContentType.MULTIPART_FORM_DATA.toString(), file);
    }


    public static void main(String args[]){
        System.out.println(fetchToken("51045443"));
    }
}
