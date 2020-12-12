package com.liangdi.wings.common.utils.http;

import okhttp3.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Liang Di
 * @since 1.0.0
 */
public class OkHttpUtils {
    private static final int READ_TIMEOUT = 100;
    private static final int CONNECT_TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 60;

    private static OkHttpUtils instance;
    private final OkHttpClient okHttpClient;

    private OkHttpUtils(){
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        // 读取超时
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        // 连接超时
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //写入超时
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient = clientBuilder.build();
    }

    /**
     * 单例模式获取 NetUtils
     *
     * @return {@link OkHttpUtils}
     */
    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtils.class) {
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    /**
     * GET，同步方式，获取网络数据
     *
     * @param url 请求地址
     * @return {@link Response}
     */
    public Response getData(String url) {
        // 构造 Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        // 将 Request 封装为 Call
        Call call = okHttpClient.newCall(request);
        // 执行 Call，得到 Response
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * POST 请求，同步方式，提交数据
     *
     * @param url        请求地址
     * @param bodyParams 请求参数
     * @return {@link Response}
     */
    public Response postData(String url, Map<String, String> bodyParams) {
        // 构造 RequestBody
        RequestBody body = setRequestBody(bodyParams);
        // 构造 Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(body).url(url).build();
        // 将 Request 封装为 Call
        Call call = okHttpClient.newCall(request);
        // 执行 Call，得到 Response
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 构造 POST 请求参数
     *
     * @param bodyParam 请求参数
     * @return {@link RequestBody}
     */
    private RequestBody setRequestBody(Map<String, String> bodyParam) {
        RequestBody body = null;
        FormBody.Builder formEncodingBuilder = new FormBody.Builder();
        if (bodyParam != null) {
            Iterator<String> iterator = bodyParam.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next();
                formEncodingBuilder.add(key, bodyParam.get(key));
            }
        }
        body = formEncodingBuilder.build();
        return body;
    }
}
