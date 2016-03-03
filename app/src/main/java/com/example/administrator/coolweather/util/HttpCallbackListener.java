package com.example.administrator.coolweather.util;

/**
 * Created by Administrator on 2016/3/2.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);

}
