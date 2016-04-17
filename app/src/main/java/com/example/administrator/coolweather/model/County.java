package com.example.administrator.coolweather.model;

/**
 * Created by Administrator on 2016/3/1.
 */
public class County {
    //区名
    private String county_name;
    //区id
    private String county_id;
    //所属城市id
    private String city_id;

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public String getCounty_id() {
        return county_id;
    }

    public void setCounty_id(String county_id) {
        this.county_id = county_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

}