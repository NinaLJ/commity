package com.example.administrator.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/8.
 */

public class Test1 extends Activity{
    private TextView test;
    private TextView test1;
    private TextView test2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
       // String url ="http://v.juhe.cn/xianxing/index";//请求接口地址
//       Map params = new HashMap();//请求参数
//        params.put("key","92ee53fbe305f2c2568680848ed6effb");//应用APPKEY(应用详细页查询)
//        params.put("dtype","json");//返回数据的格式,xml或json，默认json
//        params.put("city","beijing");//城市代码，在城市列表接口获取
//        params.put("type","1");//类型，1:今日 2:明天 3:后天，默认1
       String url = "http://v.juhe.cn/xianxing/index?dtype=json&city=beijing&type=1&key=92ee53fbe305f2c2568680848ed6effb";
        client.get(getApplicationContext(), url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                    try {
                        JSONObject result=response.getJSONObject("result");
                        //获取空气质量
                       String city = result.getString("city");
                        //获取昨天天气情况
                        //String type=data.getJSONObject("yesterday").getString("type");
                        //获取今天天气情况
                        JSONArray ss=result.getJSONArray("des");
                        //获取风向情况
                       String place =ss.getJSONObject(0).getString("place");
                        Toast.makeText(Test1.this, "哈哈", Toast.LENGTH_SHORT).show();
                       test.setText(place);
                        //test1.setText(type);
                        //test2.setText(quality);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

        });

    }
}
