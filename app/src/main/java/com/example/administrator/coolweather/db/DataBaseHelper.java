package com.example.administrator.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/4/17.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    // 建立省份表
    private static final String CREATE_PROVINCE = "create table Province ("
            + "province_name text," + "province_id text )";
    // 建立城市表
    private static final String CREATE_CITY = "create table City("
            + "city_name text," + "city_id text," + "province_id text)";
    // 建立区表
    private static final String CREATE_COUNTY = "create table County("
            + "county_name text," + "county_id text," + "city_id text)";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
