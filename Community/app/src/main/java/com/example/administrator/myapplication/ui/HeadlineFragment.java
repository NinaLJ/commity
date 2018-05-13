package com.example.administrator.myapplication.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.HomeAdapter;
import com.example.administrator.myapplication.entity.Product;
import com.example.administrator.myapplication.entity.Weather;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class HeadlineFragment extends Fragment {
    private View view;
    private LinearLayout water;
    private LinearLayout electric;
    private LinearLayout fuelgas;
    private ArrayList<Product> ls = new ArrayList<Product>();
    private ListView lv;
    private HomeAdapter mHomeAdapter;
    private LinearLayout zixun;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.headline, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findview();
        getDate();
        //getLiftDate();
        setonclickLister();
        mHomeAdapter = new HomeAdapter(getActivity(), ls);//Utils.ls
        lv.setAdapter(mHomeAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(),SeeAll.class);
//                startActivity(intent);
                Intent intent = new Intent(getActivity(), SeeAll.class);
                intent.putExtra("pid", ls.get(position).getId());
               // intent.putExtra("pid", ls.get(position-1).getId());
                startActivity(intent);
              Toast.makeText(getActivity(),"点击了第" + position + "项  " + id, Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void findview() {
        water = (LinearLayout) view.findViewById(R.id.water);
        electric = (LinearLayout) view.findViewById(R.id.electric);
        fuelgas = (LinearLayout) view.findViewById(R.id.fuelgas);
        lv=(ListView)view.findViewById(R.id.lv);
        zixun=(LinearLayout)view.findViewById(R.id.zixun);
    }

    private void setonclickLister() {
        water.setOnClickListener(myLister);
        electric.setOnClickListener(myLister);
        fuelgas.setOnClickListener(myLister);
        zixun.setOnClickListener(myLister);
    }

    View.OnClickListener myLister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.water:
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), WaterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.electric:
                    Intent intent1 = new Intent();
                    intent1.setClass(getActivity(), ElectricActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.fuelgas:
                    Intent intent2 = new Intent();
                    intent2.setClass(getActivity(), FuelgasActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.zixun:
                    Intent intent3 = new Intent();
                    intent3.setClass(getActivity(), MessageActivity.class);
                    startActivity(intent3);
                    break;
            }
        }

    };

    //模拟网络请求数据
    private void getDate() {
        ls.add(new Product(1, R.mipmap.head1, "开展“传、讲、写”家训进社区活动  ", "活动当天，黔江中学30余名教职工志愿者一起，走进文汇社区居民。现场举行了“六尺", "文汇社区西门"));
        ls.add(new Product(2, R.mipmap.head2, "保护社区环境质量自愿者活动 ", "织居民参观环境展览、垃圾填埋场、污水处理厂，了解空气、水源水质的监测情况等", "文汇社区小广场"));
        ls.add(new Product(3, R.mipmap.huodong1, "创意大赛策划方案 ", "推广方案以独特的主题,形成了特有的社区活动品牌打造品牌社区栏目,独家冠名赞助《百姓秀场》主办‘2009年“XXX”社区 创意达人大赛’活动,通过社区互动", "哈哈社区"));
        ls.add(new Product(4, R.mipmap.loading, "巴拉巴拉巴拉 ", "hahahhahhhhh", "喜羊羊社区"));

    }
    //真实的网络请求
    private void getLiftDate(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://182.61.37.142/Community/lifedate";
        client.get(HeadlineFragment.this.getActivity(), url, new JsonHttpResponseHandler() {
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
                        String place=a.getString("Activity_place");
                        String date=a.getString("Activity_time");
                        String img=a.getString("Img");
                        //ls.add(new Product(id,img,title,content,place));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

    }
}
