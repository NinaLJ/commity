package com.example.administrator.myapplication.entity;

/**
 * Created by Administrator on 2018/5/8.
 */

public class Weather {
    private int id; //设置id是为了标识每一个Item（即每一行）
    private String mTitle;  //标题
    private String mDate;//日期
    private String mType;//天气类型
    private String mWind;//风向信息
    private String mTemperature;//温度信息
    private String mTip;//一些小贴士
    private String quality;//空气质量


    public Weather(int id, String mTitle) {
        this.id = id;
        this.mTitle = mTitle;
    }

    public Weather(int id, String mDate, String mType, String mWind, String mTemperature, String mTip) {
        this.id = id;
        this.mDate = mDate;
        this.mType = mType;
        this.mWind = mWind;
        this.mTemperature = mTemperature;
        this.mTip = mTip;
    }
    public Weather(String mDate, String mType, String mWind, String mTemperature, String mTip) {
        this.mDate = mDate;
        this.mType = mType;
        this.mWind = mWind;
        this.mTemperature = mTemperature;
        this.mTip = mTip;
    }

    public Weather(int id, String mType, String mTemperature, String quality) {
        this.id = id;
        this.mType = mType;
        this.mTemperature = mTemperature;
        this.quality = quality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmWind() {
        return mWind;
    }

    public void setmWind(String mWind) {
        this.mWind = mWind;
    }

    public String getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(String mTemperature) {
        this.mTemperature = mTemperature;
    }

    public String getmTip() {
        return mTip;
    }

    public void setmTip(String mTip) {
        this.mTip = mTip;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
