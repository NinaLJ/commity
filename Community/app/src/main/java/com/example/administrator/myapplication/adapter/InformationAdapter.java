package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.entity.Information;
import com.example.administrator.myapplication.entity.Product;

import java.util.ArrayList;

// import com.mob.tools.utils.R;


/**
 * Created by Administrator on 2018/3/28
 */
public class InformationAdapter extends BaseAdapter {
    private Context context;//上下文环境，相当于传this
    //一个由数组实现的链表，相当于容器，用来放有多少个Item项，动态添加，长度不是固定的
    private ArrayList<Information> informations = new ArrayList<Information>();

    public InformationAdapter(Context context, ArrayList<Information> informations) {
        this.context = context;
        this.informations=informations;
    }

    @Override
    public int getCount() {
        return informations.size();
    }

    @Override
    public Object getItem(int i) {
        return informations.get(i);
    }

    @Override
    public long getItemId(int i) {
        return informations.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == view) {//判断是不是空控件
            //获取布局，将xml文件转换成view，即把xml文件当成view来使用
            view = LayoutInflater.from(context).inflate(R.layout.item_information, null);
        }
        ImageView informationImage = (ImageView) view.findViewById(R.id.information_image);
       informationImage.setImageResource(informations.get(i).getmImage());
       // ImgLO.initImageLoader(context);
        // ImageLoader.getInstance().displayImage(products.get(i).getmImage(), homeImage);
        TextView informationTitle = (TextView) view.findViewById(R.id.information_title);
        informationTitle.setText(informations.get(i).getmTitle());
        TextView informationTime = (TextView) view.findViewById(R.id.information_time);
        informationTime.setText(informations.get(i).getmTime() + "");
        TextView informationContent = (TextView) view.findViewById(R.id.information_content);
        informationContent.setText(informations.get(i).getmContent());
        return view;
    }
}
