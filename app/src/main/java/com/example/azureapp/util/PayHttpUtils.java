package com.example.azureapp.util;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * fileDesc
 * Created by wzk on 2021/7/10.
 * Email 1403235458@qq.com
 */

/**
 * http访问工具
 */
public class PayHttpUtils {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    //http访问client
    OkHttpClient client = new OkHttpClient();
    //发送post请求，返回String类型
    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                //添加头部
                .addHeader("Content-Type","application/json")
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
