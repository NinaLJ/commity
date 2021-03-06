package com.example.administrator.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
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
 * Created by Administrator on 2018/5/11.
 */

public class SeeAll extends Activity {
    private LinearLayout Back;
    int pid;
    private TextView seeAll_title;
    private ImageView seeAll_img;
    private TextView seeAll_content;
    private TextView seeAll_place;
    private TextView seeAll_time;

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
        setContentView(R.layout.seeall);
        findViewById();

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SeeAll.this.finish();
            }
        });
    }

    private void findViewById() {
        seeAll_title=(TextView)findViewById(R.id.xinxi_title);
        seeAll_img=(ImageView)findViewById(R.id.xinxi_img);
        seeAll_content=(TextView)findViewById(R.id.xinxi_content);
        seeAll_place=(TextView)findViewById(R.id.xinxi_place);
        seeAll_time=(TextView)findViewById(R.id.xinxi_time);

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
                    ImgLO.initImageLoader(SeeAll.this);
                    // imageUrl代表图片的URL地址，imageView代表承载图片的IMAGEVIEW控件
                    seeAll_title.setText(jsonObject.getString("User_Nickname"));
                    seeAll_place.setText(jsonObject.getString("Product_Time"));
                    seeAll_time.setText(jsonObject.getDouble("Product_Price") + "");
                    seeAll_content.setText(jsonObject.getString("Product_Describe"));
                    ImgLO.initImageLoader(getApplicationContext());
                    ImageLoader.getInstance().displayImage(jsonObject.getString("Product_Image_Url"),seeAll_img);
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
