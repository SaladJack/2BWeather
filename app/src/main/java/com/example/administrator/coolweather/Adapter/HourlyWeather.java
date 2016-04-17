package com.example.administrator.coolweather.Adapter;

/**
 * Created by Administrator on 2016/4/18.
 */
public class HourlyWeather {
    // 预测时间
    private String clock;
    // 温度
    private String tmp;
    // 降水概率
    private String pop;
    // 风力
    private String wind;

    public HourlyWeather(String clock, String tmp, String pop, String wind) {
        this.clock = clock;
        this.tmp = tmp;
        this.pop = pop;
        this.wind = wind;
    }

    public String getClock() {
        return clock;
    }

    public String getTmp() {
        return tmp;
    }

    public String getPop() {
        return pop;
    }

    public String getWind() {
        return wind;
    }
}