package com.example.administrator.myapplication.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.HomeAdapter;
import com.example.administrator.myapplication.adapter.InformationAdapter;
import com.example.administrator.myapplication.adapter.WeatherAdapter;
import com.example.administrator.myapplication.entity.Information;
import com.example.administrator.myapplication.entity.Product;
import com.example.administrator.myapplication.entity.Weather;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Weather_Information extends Activity {
    private GridView gv;
    private ArrayList<Weather> weat = new ArrayList<Weather>();
    private WeatherAdapter mWeatherAdapter;
    private TextView tt;
    private LinearLayout Back;

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
        setContentView(R.layout.weather_item);

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Weather_Information.this.finish();
            }
        });
        findViewById();
        setOnclick();
        getDate();
        mWeatherAdapter = new WeatherAdapter(Weather_Information.this, weat);//Utils.ls
        gv.setAdapter(mWeatherAdapter);

    }

    private void findViewById() {
        gv = (GridView) findViewById(R.id.gv);
        tt=(TextView)findViewById(R.id.tt);
    }

    private void setOnclick() {

    }

    //网络请求数据
    private void  getDate() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://www.sojson.com/open/api/weather/json.shtml?city=石家庄";
        client.get(Weather_Information.this, url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject data=response.getJSONObject("data");
                    JSONArray ss=data.getJSONArray("forecast");
                    for(int i=1;i<ss.length();i++){
                        JSONObject item = ss.getJSONObject(i);
                        //获取风向情况
                        String fx=item.getString("fx");
                        //获取天气情况
                        String type=item.getString("type");
                        String high=item.getString("high");
                        String low=item.getString("low");
                        String date=item.getString("date");
                        String tips=item.getString("notice");
                        weat.add(new Weather(i,date, type, fx, high,tips));
                        System.out.print("---------------"+item);
                       tt.setText(item.toString());
                        //tt.setText(item.getString("date"));
                        //Toast.makeText(Weather_Information.this, item.toString(), Toast.LENGTH_SHORT).show();
                    }

                   // info_temperature.setText(low+"\n"+high);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
