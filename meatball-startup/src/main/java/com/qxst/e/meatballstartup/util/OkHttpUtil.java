package com.qxst.e.meatballstartup.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qxst.e.meatballstartup.state.ClientApiFinal;
import okhttp3.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {
    private static final String CHARSET_NAME = "UTF-8";
    public static final MediaType JSON_Type = MediaType.parse("application/json; charset=utf-8");

    private static final OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(10,TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS);

    /**
     * 同步get
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String get(String url) throws Exception {
        Request request = new Request.Builder().url(url).build();

        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 同步get请求
     *
     * @param url
     * @param data
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String, String> data) throws Exception {
        url = getRequestUrl(url, data);
        Request request = new Request.Builder().url(url).build();

        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 异步get请求
     *
     * @param url
     * @param responseCallback
     * @return
     * @throws Exception
     */
    public static void get(String url, Callback responseCallback) throws Exception {
        Request request = new Request.Builder().url(url).build();

        enqueue(request, responseCallback);
    }

    /**
     * 异步get
     *
     * @param url
     * @param data
     * @param responseCallback
     * @return
     * @throws Exception
     */
    public static void get(String url, Map<String, String> data, Callback responseCallback) throws Exception {
        url = getRequestUrl(url, data);
        Request request = new Request.Builder().url(url).build();

        enqueue(request, responseCallback);
    }

    /**
     * 同步post json数据
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON_Type, json);
        Request request = new Request.Builder().url(url).post(body).build();

        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 同步post 键值对数据
     *
     * @param url
     * @param data
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> data) throws IOException {

        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> item : data.entrySet()) {
            formBody.add(item.getKey(), item.getValue());
        }

        RequestBody body = formBody.build();
        Request request = new Request.Builder().url(url).post(body).build();

        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 异步post json
     *
     * @param url
     * @param json
     * @param responseCallback
     * @throws IOException
     */
    public static void post(String url, String json, Callback responseCallback) throws IOException {
        RequestBody body = RequestBody.create(JSON_Type, json);
        Request request = new Request.Builder().url(url).post(body).build();

        enqueue(request, responseCallback);
    }

    /**
     * 异步post key-value
     *
     * @param url
     * @param data
     * @param responseCallback
     * @throws IOException
     */
    public static void post(String url, Map<String, String> data, Callback responseCallback) throws IOException {
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> item : data.entrySet()) {
            formBody.add(item.getKey(), item.getValue());
        }

        RequestBody body = formBody.build();
        Request request = new Request.Builder().url(url).post(body).build();

        enqueue(request, responseCallback);
    }

    /**
     * 同步put
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String put(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON_Type, json);

        Request request = new Request.Builder().url(url).put(body).build();

        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 同步put key-value
     *
     * @param url
     * @param data
     * @return
     * @throws IOException
     */
    public static String put(String url, Map<String, String> data) throws IOException {
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> item : data.entrySet()) {
            formBody.add(item.getKey(), item.getValue());
        }

        RequestBody body = formBody.build();
        Request request = new Request.Builder().url(url).put(body).build();

        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 异步put json
     *
     * @param url
     * @param json
     * @throws IOException
     */
    public static void put(String url, String json, Callback responseCallback) throws IOException {
        RequestBody body = RequestBody.create(JSON_Type, json);

        Request request = new Request.Builder().url(url).put(body).build();
        enqueue(request, responseCallback);
    }

    /**
     * 异步put key-value
     *
     * @param url
     * @param data
     * @param responseCallback
     * @throws IOException
     */
    public static void put(String url, Map<String, String> data, Callback responseCallback) throws IOException {
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> item : data.entrySet()) {
            formBody.add(item.getKey(), item.getValue());
        }

        RequestBody body = formBody.build();
        Request request = new Request.Builder().url(url).put(body).build();

        enqueue(request, responseCallback);
    }

    /**
     * 通用同步请求。
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return okHttpClient.build().newCall(request).execute();
    }

    /**
     * 通用异步请求
     *
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback) {
        okHttpClient.build().newCall(request).enqueue(responseCallback);
    }

    /**
     * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
     *
     * @param request
     */
    public static void enqueue(Request request) {
        okHttpClient.build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //
            }
        });
    }

    public static String getStringFromServer(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        if (response.isSuccessful()) {
            String responseUrl = response.body().string();
            return responseUrl;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    /**
     * 这里使用了HttpClinet的API。只是为了方便
     *
     * @param params
     * @return
     */
    public static String formatParams(List<BasicNameValuePair> params) {
        return URLEncodedUtils.format(params, CHARSET_NAME);
    }

    /**
     * 为HttpGet 的 url 方便的添加多个name value 参数。
     *
     * @param url
     * @param params
     * @return
     */
    public static String attachHttpGetParams(String url, List<BasicNameValuePair> params) {
        return url + "?" + formatParams(params);
    }

    /**
     * 为HttpGet 的 url 方便的添加1个name value 参数。
     *
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String attachHttpGetParam(String url, String name, String value) {
        return url + "?" + name + "=" + value;
    }
    /**
     * http请求： httpGet
     * @param url
     * @param url
     * @return
     * @throws IOException
     */
    public static String httpGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = okHttpClient.build().newCall(request).execute();
        return response.body().string(); // 返回的是string 类型，json的mapper可以直接处理
    }

    /**
     * http请求： httpPost
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String httpPost(String url, String json) throws IOException {
        RequestBody requestBody = RequestBody.create(JSON_Type, json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = okHttpClient.build().newCall(request).execute();
        return response.body().string();
    }

    /**
     * get方式URL拼接
     *
     * @param url
     * @param map
     * @return
     */
    private static String getRequestUrl(String url, Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return url;
        } else {
            StringBuilder newUrl = new StringBuilder(url);
            if (url.indexOf("?") == -1) {
                newUrl.append("?rd=" + Math.random());
            }

            for (Map.Entry<String, String> item : map.entrySet()) {
                try {
                    newUrl.append("&" + item.getKey().trim() + "=" + URLEncoder.encode(String.valueOf(item.getValue()).trim(), "UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return newUrl.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        String json = OkHttpUtil.httpGet(ClientApiFinal.zzlb+ClientApiFinal.goodsType);
        Map map = JSON.parseObject(json,Map.class);
        JSONArray content = (JSONArray) map.get("content");
        for (Object o: content) {
            JSONObject jsonObject = (JSONObject)o;
            System.out.println(jsonObject.get("name"));
            System.out.println(jsonObject.get("icon"));
            System.out.println(jsonObject.get("objectId"));
        }
    }
}
