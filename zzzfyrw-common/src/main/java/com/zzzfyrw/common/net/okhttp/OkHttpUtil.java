/**
 * FileName: OkHttpUtil
 * Author:   dpz
 * Date:     2018/12/10 22:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * dpz         2018/12/10 22:56        1.0               新建类
 */
package com.zzzfyrw.common.net.okhttp;


import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 〈〉
 * @author dpz
 * created 2018/12/10 22:56
 */
public class OkHttpUtil {
    public enum OkHttpHelper{
        INSTANCE;
        private OkHttpClient OkHttpClient;
        OkHttpHelper(){
            this.OkHttpClient=new OkHttpClient();
            OkHttpClient.Builder builder = this.OkHttpClient.newBuilder();
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(60,TimeUnit.SECONDS);
            builder.writeTimeout(60,TimeUnit.SECONDS);
            TrustManager[] trustAllCerts = buildTrustManagers();
            final SSLContext sslContext;
            try {
                sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new SecureRandom());
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
                builder.hostnameVerifier((hostname, session) -> true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            builder.build();
        }
        public OkHttpClient getOkHttpClient() {
            return OkHttpClient;
        }
    }

    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }

    private static OkHttpClient getInstance(){
        return OkHttpHelper.INSTANCE.getOkHttpClient();
    }

    public static String get(String url){
        Request request = buildRequest(url, HttpMethodEnum.GET,null,null,null);
        return doRequest(request);
    }


    public static String get(String url,Map<String,String> params){
        Request request = buildRequest(url, HttpMethodEnum.GET,params,null,null);
        return doRequest(request);
    }

    public static String get(String url,Map<String,String> params,Map<String,String> headers){
        Request request = buildRequest(url, HttpMethodEnum.GET,params,null,headers);
        return doRequest(request);
    }

    public static String post(String url){
        Request request = buildRequest(url, HttpMethodEnum.POST,null,null,null);
        return doRequest(request);
    }

    public static String post(String url,String json){
        Request request = buildRequest(url, HttpMethodEnum.POST,null,json,null);
        return doRequest(request);
    }

    public static Response postRP(String url,String json){
        Request request = buildRequest(url, HttpMethodEnum.POST,null,json,null);
        return doRequestRP(request);
    }

    public static String post(String url,String json,Map<String,String> headers){
        Request request = buildRequest(url, HttpMethodEnum.POST,null,json,headers);
        return doRequest(request);
    }

    public static Response postRP(String url,String json,Map<String,String> headers){
        Request request = buildRequest(url, HttpMethodEnum.POST,null,json,headers);
        return doRequestRP(request);
    }

    public static String post(String url,Map<String,String> params){
        Request request = buildRequest(url, HttpMethodEnum.POST,params,null,null);
        return doRequest(request);
    }

    public static String post(String url,Map<String,String> params,Map<String,String> headers){
        Request request = buildRequest(url, HttpMethodEnum.POST,params,null,headers);
        return doRequest(request);
    }

    /**
     * 文件下载
     * @param url 文件地址
     * @return 返回字节数据流
     */
    public static InputStream downFile(String url){
        try{
            Request request = buildRequest(url, HttpMethodEnum.GET,null,null,null);
            Response response = getInstance().newCall(request).execute();
            return response.body().byteStream();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private static String doRequest(Request request){
        Response response;
        try {
            response = getInstance().newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Response doRequestRP(Request request){
        Response response = null;
        try {
            response = getInstance().newCall(request).execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    /**
     * form/data 单文件上传
     * @param url 请求地址
     * @param file 文件
     * @param fileKey  文件key
     * @param params 其它请求参数
     * @param requestHeader 请求头
     * @return 返回内容
     */
    public static String multipartImagePost(String url, File file, String fileKey, Map<String,String> params, Map<String,String> requestHeader)  {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(Objects.requireNonNull(MediaType.parse("multipart/form-data")));
        if(file != null){
            RequestBody fileBody;
            if(file.getName().contains("jpg") || file.getName().contains("jpeg")){ //只支持两种格式，后续加
                fileBody = RequestBody.create(MediaType.parse("image/jpeg"),file);
            }else {
                fileBody = RequestBody.create(MediaType.parse("image/png"),file);
            }
            builder.addFormDataPart(fileKey, file.getName(), fileBody);
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if(entry.getValue() == null){
                builder.addFormDataPart(entry.getKey(),"");
            }else {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        Request.Builder requestBuilder = new Request.Builder()
                .post(builder.build())
                .url(url);
        setHeaders(requestHeader,requestBuilder);

        try {
            Response execute = getInstance().newCall(requestBuilder.build()).execute();
            return execute.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static Request buildRequest(String url, HttpMethodEnum methodEnum, Map<String,String> params, String json, Map<String,String> headerMap){
        Request.Builder builder = new Request.Builder().url(url);
        switch (methodEnum){
            case GET:
                builder.get();
                builderUrl(params,builder,url);
                setHeaders(headerMap, builder);
                break;
            case POST:
                RequestBody body = null;
                if(json != null){
                    body = RequestBody.create(MediaType.parse("application/json"),json);
                }else if(params != null){
                    body = builderFormUrlencoded(params);
                }
                setHeaders(headerMap, builder);
                assert body != null;
                builder.post(body);
                break;
        }
        return builder.build();
    }

    private static void setHeaders(Map<String, String> headerMap, Request.Builder builder) {
        if (headerMap != null) {
            Headers.Builder headers = new Headers.Builder();
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
            builder.headers(headers.build());
        }else {
            builder.addHeader("Connection","close");
        }
    }

    private static RequestBody builderFormUrlencoded(Map<String,String> map){
        FormBody.Builder formBody = new FormBody.Builder();
        if(map != null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if(entry.getValue() == null){
                    formBody.add(entry.getKey(),"");
                }else {
                    formBody.add(entry.getKey(),entry.getValue());
                }
            }
        }
        return formBody.build();
    }

    private static void builderUrl(Map<String,String> map,Request.Builder builder,String url){
        if(map != null){
            boolean first = true;
            StringBuilder buffer = new StringBuilder();
            if(!url.contains("?")){
                buffer.append("?");
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if(!first)
                    buffer.append("&");
                buffer.append(entry.getKey());
                buffer.append("=");
                buffer.append(entry.getValue());
                first = false;
            }
            builder.url(url+buffer.toString());
        }
    }

}
