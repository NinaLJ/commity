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


import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.InformationAdapter;
import com.example.administrator.myapplication.entity.Information;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyCollection extends Activity {
    private LinearLayout Back;
    private ArrayList<Information> itemDetailArrayList = new ArrayList<>();
//    private ListView list_mycol;
    private ListView lv;
    private LinearLayout jvzi;

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
        setContentView(R.layout.my_collection);
        Back = (LinearLayout) findViewById(R.id.back);
        jvzi=(LinearLayout)findViewById(R.id.jvzi_shoucang);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyCollection.this.finish();
            }
        });
        jvzi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(MyCollection.this, Ycollect.class);
                startActivity(intent1);

            }
        });

        RequestParams params = new RequestParams();
        JSONObject param_json = new JSONObject();
        int User_Id = 2014011845;       //这里要获取登陆的账号id
        try {
            param_json.put("User_Id",User_Id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("User_Id",param_json);
        String str_url = "http://182.61.37.142/Community/analysis/request_col?User_Id={User_Id:"+Login.mUserId+"}";      //这里写死了
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(str_url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject content = response.getJSONObject(i).getJSONObject("good").getJSONObject("content");
                       //这儿是有问题的
                        itemDetailArrayList.add(new Information(content.getInt("Product_Id"),content.getInt("Product_Image_Url"),content.getString("Product_Name"),
                                content.getString("Product_Describe"),content.getString("Product_Price")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),response.toString(), Toast.LENGTH_SHORT).show();
                lv = (ListView)findViewById(R.id.lv);
                InformationAdapter adapter = new InformationAdapter(getApplicationContext(),itemDetailArrayList);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(listener);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(getApplicationContext(),"JSONObject", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                Toast.makeText(getApplicationContext(),"String", Toast.LENGTH_SHORT).show();
            }
        });
//        list_mycol = (ListView)findViewById(android.R.id.list);
//        list_mycol.setOnItemClickListener(listener);
    }


    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(MyCollection.this, MainActivity.class);
            //i.putExtra("pid",position);
            i.putExtra("pid",itemDetailArrayList.get(position).getId());
            startActivity(i);
        }
    };

}
