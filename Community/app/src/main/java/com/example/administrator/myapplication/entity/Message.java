package com.example.administrator.myapplication.entity;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class Message {
    private int id; //设置id是为了标识每一个Item（即每一行）
    private String mTitle;  //标题
    private String mContent; //内容
    private String mTime;  //展示时间
    private String mContacts;//展示联系人
    private String mIphone;//展示联系电话

    public Message(int id, String mTitle, String mContent, String mTime, String mContacts, String mIphone) {
        this.id = id;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mTime = mTime;
        this.mContacts = mContacts;
        this.mIphone = mIphone;
    }
    public Message(int id, String mTitle, String mContent, String mTime) {
        this.id = id;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mTime = mTime;
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

    public String getmContacts() {
        return mContacts;
    }

    public void setmContacts(String mContacts) {
        this.mContacts = mContacts;
    }

    public String getmIphone() {
        return mIphone;
    }

    public void setmIphone(String mIphone) {
        this.mIphone = mIphone;
    }
}
