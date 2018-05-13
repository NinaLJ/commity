package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.entity.Message;
import com.example.administrator.myapplication.entity.Weather;

import java.util.ArrayList;

// import com.mob.tools.utils.R;


/**
 * Created by Administrator on 2018/3/28
 */
public class WeatherAdapter extends BaseAdapter {
    private Context context;//上下文环境，相当于传this
    //一个由数组实现的链表，相当于容器，用来放有多少个Item项，动态添加，长度不是固定的
    private ArrayList<Weather> weather= new ArrayList<Weather>();

    public WeatherAdapter(Context context, ArrayList<Weather> weather) {
        this.context = context;
        this.weather=weather;
    }

    @Override
    public int getCount() {
        return weather.size();
    }

    @Override
    public Object getItem(int i) {
        return weather.get(i);
    }

    @Override
    public long getItemId(int i) {
        return weather.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (null == view) {//判断是不是空控件
            //获取布局，将xml文件转换成view，即把xml文件当成view来使用
            view = LayoutInflater.from(context).inflate(R.layout.activity_weather__information, null);
            if (i % 2 == 0) {
                view.setBackgroundColor(Color.parseColor("#FFCC00"));
            } else {
                view.setBackgroundColor(Color.parseColor("#8A8A8A"));
            }
        }
        TextView weatherDate = (TextView) view.findViewById(R.id.date);
        weatherDate.setText(weather.get(i).getmDate());
        TextView weatherType = (TextView) view.findViewById(R.id.type);
        weatherType.setText(weather.get(i).getmType());
        TextView weatherWind = (TextView) view.findViewById(R.id.wind);
        weatherWind.setText(weather.get(i).getmWind());
        TextView wendu=(TextView)view.findViewById(R.id.wendu);
        wendu.setText(weather.get(i).getmTemperature());
        TextView tips=(TextView)view.findViewById(R.id.tips);
        tips.setText(weather.get(i).getmTip());
        return view;
    }
}
