package com.example.administrator.coolweather.util;

/**
 * Created by Administrator on 2016/3/2.
 */
public interface HttpCallbackListener {
    // 成功时回调
    void onFinish(String response);
    // 出错时回调
    void onError(Exception e);

}
