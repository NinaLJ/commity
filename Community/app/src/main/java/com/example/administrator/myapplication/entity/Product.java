package com.example.administrator.myapplication.entity;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class Product {
    private int id; //设置id是为了标识每一个Item（即每一行）
    private int mImage; //图片
    private String mTitle;  //标题
    private Double mPrice;  //价格
    private String mContent; //内容
    private String mTime;  //展示时间
    private String mAddress;//展示地址

    public Product(int id, int mImage, String mTitle, String mContent,String mAddress) {
        this.id = id;
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mAddress=mAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }
}
