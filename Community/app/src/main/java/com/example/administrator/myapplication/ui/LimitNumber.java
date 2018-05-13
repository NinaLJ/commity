package com.example.administrator.myapplication.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LimitNumber extends Activity {
    private LinearLayout Back;
    private TextView date_limit;
    private TextView week_limit;
    private TextView shijia_limit;
    private TextView shijia_place;
    private TextView gongwu_limit;
    private TextView gongwu_plac;

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
        setContentView(R.layout.limitnumber);

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LimitNumber.this.finish();
            }
        });
        findViewById();
        setonClick();
        getLimit();
    }

    private void setonClick() {
        
        
    }
    private void findViewById() {
        date_limit=(TextView)findViewById(R.id.date);
        week_limit=(TextView)findViewById(R.id.week);
        shijia_limit=(TextView)findViewById(R.id.sijia);
        shijia_place=(TextView)findViewById(R.id.sijia_place);
        gongwu_limit=(TextView)findViewById(R.id.gongwu);
        gongwu_plac=(TextView)findViewById(R.id.gongwu_place);
    }
    //请求限行限号的API
    private void getLimit(){
        AsyncHttpClient client = new AsyncHttpClient();
        // String url ="http://v.juhe.cn/xianxing/index";//请求接口地址
//       Map params = new HashMap();//请求参数
//        params.put("key","92ee53fbe305f2c2568680848ed6effb");//应用APPKEY(应用详细页查询)
//        params.put("dtype","json");//返回数据的格式,xml或json，默认json
//        params.put("city","beijing");//城市代码，在城市列表接口获取
//        params.put("type","1");//类型，1:今日 2:明天 3:后天，默认1
        String url = "http://v.juhe.cn/xianxing/index?dtype=json&city=beijing&type=1&key=c1079b9cc579f0cc30f9f36bafc88273";
        client.get(LimitNumber.this, url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject result=response.getJSONObject("result");
                    String city = result.getString("city");
                    String date=result.getString("date");
                    String week=result.getString("week");
                    JSONArray ss=result.getJSONArray("des");
                    // JSONObject weihao=result.getJSONObject("xxweihao");
                    //String xweihao=weihao.toString();
                    String place =ss.getJSONObject(0).getString("place");
                    String time=ss.getJSONObject(0).getString("time");
                    String gongwu=ss.getJSONObject(1).getString("time");
                    String gongwu_place=ss.getJSONObject(1).getString("place");

                    //Toast.makeText(getActivity() , "哈哈", Toast.LENGTH_SHORT).show();
                    date_limit.setText(date);
                    week_limit.setText(week);
                    shijia_limit.setText(time);
                    shijia_place.setText(place);
                    gongwu_limit.setText(gongwu);
                    gongwu_plac.setText(gongwu_place);

                    //test1.setText(type);
                    //test2.setText(quality);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }

}
