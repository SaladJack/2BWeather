package com.example.administrator.coolweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.example.administrator.coolweather.Adapter.HourlyWeather;
import com.example.administrator.coolweather.activity.WeatherActivity;
import com.example.administrator.coolweather.db.WeatherDB;
import com.example.administrator.coolweather.model.City;
import com.example.administrator.coolweather.model.County;
import com.example.administrator.coolweather.model.Province;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/3/2.
 */
public class Utility {

    //处理服务器返回的省级数据
    public static boolean handleProvincesResponse(WeatherDB weatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");//以“，”对字符串response进行分组
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");//以“|”对字符串分组
                    Province province = new Province();
                    province.setProvince_id(array[0]);
                    province.setProvince_name(array[1]);
                    weatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    // 处理服务器返回的市级数据
    public static boolean handleCitiesResponse(WeatherDB weatherDB,
                                               String response, String provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCity_id(array[0]);
                    city.setCity_name(array[1]);
                    city.setProvince_id(provinceId);
                    weatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    // 处理服务器返回的县级数据
    public static boolean handleCountiesResponse(WeatherDB weatherDB,
                                                 String response, String cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCounty_id(array[0]);
                    county.setCounty_name(array[1]);
                    county.setCity_id(cityId);
                    weatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }

    // 处理服务器返回的json数据
    public static void handleWeatherResponse(Context context, String response) {
        try {
            JSONObject jsonobject = new JSONObject(response);
            JSONArray title = jsonobject
                    .getJSONArray("HeWeather data service 3.0");
            JSONObject first_object = (JSONObject) title.get(0);
            JSONObject basic = (JSONObject) first_object.get("basic");
            JSONObject update = (JSONObject) basic.get("update");
            JSONArray daily_forecast = (JSONArray) first_object
                    .get("daily_forecast");
            JSONObject daily_forecast_first = (JSONObject) daily_forecast
                    .get(0);
            JSONObject cond = (JSONObject) daily_forecast_first.get("cond");
            JSONObject tmp = (JSONObject) daily_forecast_first.get("tmp");
            JSONObject astro = (JSONObject) daily_forecast_first.get("astro");
            JSONObject wind = (JSONObject) daily_forecast_first.get("wind");
            JSONArray hourly_forecast = (JSONArray) first_object
                    .get("hourly_forecast");
            WeatherActivity.weatherList.clear();
            for (int i = 0; i < hourly_forecast.length(); i++) {
                JSONObject json = hourly_forecast.getJSONObject(i);
                JSONObject json_wind = (JSONObject) json.get("wind");
                String date = json.getString("date");
                String[] array = date.split(" ");
                String dir = json_wind.getString("dir");
                String sc = json_wind.getString("sc");
                String hourly_clock = array[1];
                String hourly_tmp = "温度：" + json.getString("tmp") + "℃";
                String hourly_pop = "降水概率：" + json.getString("pop");
                String hourly_wind = "风力：" + dir + " " + sc + "级";
                HourlyWeather wea = new HourlyWeather(hourly_clock, hourly_tmp,
                        hourly_pop, hourly_wind);
                WeatherActivity.weatherList.add(wea);
            }
            String sr = astro.getString("sr");
            String ss = astro.getString("ss");
            String pop = daily_forecast_first.getString("pop");
            String windText = wind.getString("dir") + " "
                    + wind.getString("sc") + "级";
            String city_name = basic.getString("city");
            String publish_time = "发布时间：" + update.getString("loc");
            String weather = "日：" + cond.getString("txt_d") + "   夜："
                    + cond.getString("txt_n");
            String min = tmp.getString("min");
            String max = tmp.getString("max");
            saveWeatherInfo(context, city_name, publish_time, weather, min,
                    max, sr, ss, windText, pop);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static void saveWeatherInfo(Context context, String city_name,
                                        String publish_time, String weather, String min, String max,
                                        String sr, String ss, String windText, String pop) {
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(context).edit();
        editor.putString("city_name", city_name);
        editor.putString("publish_time", publish_time);
        editor.putString("weather", weather);
        editor.putString("minTmp", min + "℃");
        editor.putString("maxTmp", max + "℃");
        editor.putString("sr", sr);
        editor.putString("ss", ss);
        editor.putString("windText", windText);
        editor.putString("pop", pop);
        editor.commit();
    }
}