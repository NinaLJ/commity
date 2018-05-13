package com.example.administrator.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.entity.Weather;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2018/5/8.
 */

public class Test extends Activity{
    private TextView test;
    private TextView test1;
    private TextView test2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        find();
        getDataWeather();
    }



    private void find() {
        test= (TextView)findViewById(R.id.test);
        test1= (TextView)findViewById(R.id.test1);
        test2= (TextView)findViewById(R.id.test2);

    }
    private void  getDataWeather() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://www.sojson.com/open/api/weather/json.shtml?city=石家庄";
        client.get(getApplicationContext(), url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                    try {
                        JSONObject data=response.getJSONObject("data");
                        //获取空气质量
                        String quality = data.getString("quality");
                        //获取昨天天气情况
                        //String type=data.getJSONObject("yesterday").getString("type");
                        //获取今天天气情况
                        JSONArray ss=data.getJSONArray("forecast");
                        //获取风向情况
                        String fx=ss.getJSONObject(0).getString("fx");
                        //获取天气情况
                        String type=ss.getJSONObject(0).getString("type");
                        String high=ss.getJSONObject(0).getString("high");
                        String low=ss.getJSONObject(0).getString("low");
                        Toast.makeText(Test.this, "哈哈", Toast.LENGTH_SHORT).show();
                       // test.setText(low+"~"+high);
                        //test1.setText(type);
                        //test2.setText(quality);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

        });

    }
}
