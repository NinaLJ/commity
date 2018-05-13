package com.example.administrator.myapplication.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.HomeAdapter;
import com.example.administrator.myapplication.adapter.InformationAdapter;
import com.example.administrator.myapplication.entity.Information;
import com.example.administrator.myapplication.entity.Message;
import com.example.administrator.myapplication.entity.Product;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InformationFragment extends Fragment {
    private View view;
    private LinearLayout back;
    private ListView lv_information;
    private ArrayList<Information> ls_infor = new ArrayList<Information>();
    private InformationAdapter mInformationAdapter;
    private TextView info_type;
    private TextView info_temperature;
    private TextView info_quality;
    private LinearLayout info_weather;
    private LinearLayout info_limitnumber;
    private TextView tv_erar;
    private TextView limi_time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.information,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getview();
        getDataWeather();
        getDate();
        setListener();
        getCarLimit();
        mInformationAdapter = new InformationAdapter(getActivity(), ls_infor);//Utils.ls
        lv_information.setAdapter(mInformationAdapter);
        lv_information.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(),Details.class);
//                startActivity(intent);
                Intent intent = new Intent(getActivity(), Details.class);
                intent.putExtra("pid", ls_infor.get(position).getId());
                // intent.putExtra("pid", ls.get(position-1).getId());
                startActivity(intent);
                Toast.makeText(getActivity(),"点击了第" + position + "项  " + id, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getview(){
        back=(LinearLayout)view.findViewById(R.id.back);
        lv_information=(ListView)view.findViewById(R.id.lv_information);
        info_type=(TextView)view.findViewById(R.id.info_type);
        info_temperature=(TextView)view.findViewById(R.id.info_temperature);
        info_quality=(TextView)view.findViewById(R.id.info_quality);
        info_weather=(LinearLayout)view.findViewById(R.id.weather_info);
        info_limitnumber=(LinearLayout)view.findViewById(R.id.limitnumber_info);
        tv_erar=(TextView)view.findViewById(R.id.tv_erar);
        limi_time=(TextView)view.findViewById(R.id.limit_time);

    }
    private void setListener(){
        back.setOnClickListener(myListener);
        info_weather.setOnClickListener(myListener);
        info_limitnumber.setOnClickListener(myListener);

    }
    private View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    Intent i = new Intent();
                    i.setClass(getActivity(), Home.class);
                    startActivity(i);
                    break;
                case R.id.weather_info:
                    Intent j = new Intent();
                    j.setClass(getActivity(),Weather_Information.class);
                    startActivity(j);
                    break;
                case R.id.limitnumber_info:
                    Intent k = new Intent();
                    k.setClass(getActivity(),LimitNumber.class);
                    startActivity(k);
                    break;
            }

        }
    };
    //网络请求数据
    private void getDate() {
        ls_infor.add(new Information(1, R.mipmap.lv1, "教你如何轻松去除茶垢 ", "茶叶中包含茶多酚，和空气和水接触，", "2018-3-29"));
        ls_infor.add(new Information(2, R.mipmap.lv2, "橙子全身都是宝 你用对了吗？ ", "运动后饮用橙汁，含量丰富的果糖能迅速补充体力，", "2018-3-28"));
        ls_infor.add(new Information(3, R.mipmap.lv3, "保鲜新鲜果蔬的小技巧 ", "香蕉：将香蕉的顶部用保鲜膜包住，可以多放3-5天。由于香蕉可以释放大量乙烯气体，具有催熟作用，所以应该单独放。", "2018-3-28"));
        ls_infor.add(new Information(4, R.mipmap.loading, "折叠衣服有妙招 ", "hahahhahhhhh", "2018-3-27"));
        ls_infor.add(new Information(5, R.mipmap.loading, "哈哈哈哈哈哈哈哈哈", "hahahhahhhhh", "2018-3-26"));
    }
    //请求天气的API
    private void  getDataWeather() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://www.sojson.com/open/api/weather/json.shtml?city=石家庄";
        client.get(InformationFragment.this.getActivity(), url, new JsonHttpResponseHandler() {
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
                    Toast.makeText(getActivity() , "哈哈", Toast.LENGTH_SHORT).show();
                    info_temperature.setText(low+"\n"+high);
                    info_quality.setText(type);
                    info_quality.setText(quality);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }
    //请求限行限号的API
    private void getCarLimit(){
        AsyncHttpClient client = new AsyncHttpClient();
        // String url ="http://v.juhe.cn/xianxing/index";//请求接口地址
//       Map params = new HashMap();//请求参数
//        params.put("key","92ee53fbe305f2c2568680848ed6effb");//应用APPKEY(应用详细页查询)
//        params.put("dtype","json");//返回数据的格式,xml或json，默认json
//        params.put("city","beijing");//城市代码，在城市列表接口获取
//        params.put("type","1");//类型，1:今日 2:明天 3:后天，默认1
        String url = "http://v.juhe.cn/xianxing/index?dtype=json&city=beijing&type=1&key=c1079b9cc579f0cc30f9f36bafc88273";
        client.get(InformationFragment.this.getActivity(), url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject result=response.getJSONObject("result");
                    String city = result.getString("city");
                    JSONArray ss=result.getJSONArray("des");
                   // JSONObject weihao=result.getJSONObject("xxweihao");
                    //String xweihao=weihao.toString();
                    String place =ss.getJSONObject(0).getString("place");
                    String time=ss.getJSONObject(0).getString("time");
                    //Toast.makeText(getActivity() , "哈哈", Toast.LENGTH_SHORT).show();
                    tv_erar.setText(place);
                    limi_time.setText(time);
                    //test1.setText(type);
                    //test2.setText(quality);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }
    private void getLifeDate(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://www.sojson.com/open/api/weather/json.shtml?city=石家庄";
        client.get(InformationFragment.this.getActivity(), url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        //获取的是外层的属性，外层的属性默认放在response中
                        JSONObject a = response.getJSONObject(i);
                        int id=a.getInt("Id");
                        String title = a.getString("Activity_Name");
                        String img=a.getString("Activity_Img");
                        String content= a.getString("Activity_Img");
                        String date=a.getString("Activity_time");
                        //ls_infor.add(new Information(id,img,title,content,date));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

    }



}
