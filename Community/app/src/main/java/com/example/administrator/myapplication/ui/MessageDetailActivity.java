package com.example.administrator.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.util.ImgLO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/3/28.
 */

public class MessageDetailActivity extends Activity {
    private LinearLayout Back;
    int pid;
    private TextView mess_title;
    private TextView mess_content;
    private TextView mess_person;
    private TextView mess_tel;
    private TextView mess_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.message_details);

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MessageDetailActivity.this.finish();
            }
        });
        findViewById();
        setOnclick();
    }

    private void setOnclick() {
    }

    private void findViewById() {
        mess_title=(TextView)findViewById(R.id.message_title);
        mess_content=(TextView)findViewById(R.id.message_content);
        mess_person=(TextView)findViewById(R.id.message_person);
        mess_tel=(TextView)findViewById(R.id.message_tel);
        mess_date=(TextView)findViewById(R.id.message_date);

    }
    @Override
    protected void onStart() {
        super.onStart();

        //获取从上一个页面带来的商品id
        Intent i = getIntent();
        pid = i.getIntExtra("pid",0);
//        Toast.makeText(getApplicationContext(),"从上个页面带来的pid：" + pid +"",Toast.LENGTH_SHORT).show();
        RequestParams params = new RequestParams();
        JSONObject params_json = new JSONObject();
        try {
            params_json.put("Product_Id",pid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("pId",params_json);
        String str_url = "http://182.61.37.142/IslandTrading/analysis/lookupprice";
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(str_url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    System.out.println("----得到的：" + response.toString());
                    JSONObject jsonObject = response.getJSONObject("PRODUCT").getJSONObject("content");
                    System.out.println("-----得到的字符串" + jsonObject.toString() + "     \n" + response.toString());
                    JSONObject content = response.getJSONObject("PRODUCT").getJSONObject("content");
                    //hx_username = content.getString("Hx_Username");

                    //初始化ImageLoader
                    ImgLO.initImageLoader(MessageDetailActivity.this);
                    // imageUrl代表图片的URL地址，imageView代表承载图片的IMAGEVIEW控件
                    mess_title.setText(jsonObject.getString("User_Nickname"));
                    mess_person.setText(jsonObject.getString("Product_Time"));
                    mess_tel.setText(jsonObject.getDouble("Product_Price") + "");
                    mess_content.setText(jsonObject.getString("Product_Describe"));
//                    ImgLO.initImageLoader(getApplicationContext());
//                    ImageLoader.getInstance().displayImage(jsonObject.getString("Product_Image_Url"),seeAll_img);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                System.out.println("----错误：" + responseString);
            }
        });
    }
}
