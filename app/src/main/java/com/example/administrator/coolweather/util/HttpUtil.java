package com.example.administrator.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/3/2.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    //数据请求方式
                    connection.setRequestMethod("GET");
                    //设置连接超时时间
                    connection.setConnectTimeout(8000);
                    //设计读取超时时间
                    connection.setReadTimeout(8000);
                    connection.setRequestProperty("apikey",
                            "bfa7642794ced26d2d85175f2e8c7900");
                    connection.connect();
                    //获取输入流
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in, "UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
