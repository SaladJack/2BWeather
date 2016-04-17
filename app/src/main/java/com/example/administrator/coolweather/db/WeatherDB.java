package com.example.administrator.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.coolweather.model.City;
import com.example.administrator.coolweather.model.County;
import com.example.administrator.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/17.
 */

//实现各种数据库操作。
public class WeatherDB {

    // 数据库名
    private static final String DataBaseName = "SBWeather";

    private static final int VERSION = 1;

    private SQLiteDatabase db;

    private static WeatherDB weatherDB;




    //构造函数私有化,并创建数据库
    private WeatherDB(Context context) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context,
                DataBaseName, null, VERSION);
        db = dataBaseHelper.getWritableDatabase();
    }




    // 获取数据库的唯一实例
    public static WeatherDB getInstance(Context context) {
        if (weatherDB == null) {
            weatherDB = new WeatherDB(context);
            return weatherDB;
        }
        return weatherDB;
    }








    // 保存省级数据
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvince_name());
            values.put("province_id", province.getProvince_id());
            db.insert("Province", null, values);
        }
    }

    // 保存市级数据
    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCity_name());
            values.put("city_id", city.getCity_id());
            values.put("province_id", city.getProvince_id());
            db.insert("City", null, values);
        }
    }

    // 保存乡级数据
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCounty_name());
            values.put("county_id", county.getCounty_id());
            values.put("city_id", county.getCity_id());
            db.insert("County", null, values);
        }
    }












    // 返回本地所有省级数据
    public List<Province> loadProvinces(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setProvince_name(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvince_id(cursor.getString(cursor.getColumnIndex("province_id")));
                list.add(province);
            }while(cursor.moveToNext());
        }
        return list;
    }

    // 返回本地所有市级数据
    public List<City> loadCities(String provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?",
                new String[] { provinceId }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setCity_name(cursor.getString(cursor
                        .getColumnIndex("city_name")));
                city.setCity_id(cursor.getString(cursor
                        .getColumnIndex("city_id")));
                city.setProvince_id(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }

    // 返回本地所有乡级数据
    public List<County> loadCounties(String cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "city_id=?",
                new String[] { cityId }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setCounty_name(cursor.getString(cursor
                        .getColumnIndex("county_name")));
                county.setCounty_id(cursor.getString(cursor
                        .getColumnIndex("county_id")));
                county.setCity_id(cityId);
                list.add(county);
            } while (cursor.moveToNext());
        }
        return list;
    }









}
