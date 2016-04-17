package com.example.administrator.coolweather.model;

/**
 * Created by Administrator on 2016/3/1.
 */
public class City {
    //城市名
    private String city_name;
    //城市id
    private String city_id;
    //所属省份id
    private String province_id;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

}