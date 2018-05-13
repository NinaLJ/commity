package com.example.administrator.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.HomeAdapter;
import com.example.administrator.myapplication.adapter.MessageAdapter;
import com.example.administrator.myapplication.entity.Message;
import com.example.administrator.myapplication.entity.Product;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/28.
 */

public class MessageActivity extends Activity {
    private ListView lv;
    private ArrayList<Message> mess = new ArrayList<Message>();
    private MessageAdapter mMessageAdapter  ;
    private LinearLayout Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setContentView(R.layout.message);

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MessageActivity.this.finish();
            }
        });

        findview();
        getDate();
        //getMessageDate();
        setOnclick();
        mMessageAdapter = new MessageAdapter(MessageActivity.this, mess);//Utils.ls
        lv.setAdapter(mMessageAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MessageActivity.this, MessageDetailActivity.class);
                intent.putExtra("pid", mess.get(position).getId());
                // intent.putExtra("pid", ls.get(position-1).getId());
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"点击了第" + position + "项  " + id, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void findview() {
        lv=(ListView)findViewById(R.id.lv_message);
    }

    private void setOnclick() {
    }
    //网络请求数据
    private void getDate() {
        mess.add(new Message(1, "有关停水通知", "停水啦，停水啦，停水啦", "2018年3月28日"));
        mess.add(new Message(2, "有关停电通知", "停电啦，停电啦，停电啦", "2018年3月28日"));

    }
    //真实的网络请求
    private void getMessageDate(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://www.sojson.com/open/api/weather/json.shtml?city=石家庄";
        client.get(MessageActivity.this, url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        //获取的是外层的属性，外层的属性默认放在response中
                        JSONObject a = response.getJSONObject(i);
                        int id=a.getInt("Id");
                        String title = a.getString("Activity_Name");
                        String content= a.getString("Activity_Img");
                        String date=a.getString("Activity_time");
                        mess.add(new Message(id,title,content,date));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

    }
}
