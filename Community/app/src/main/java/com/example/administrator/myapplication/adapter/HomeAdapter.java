package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.entity.Product;
import com.example.administrator.myapplication.util.ImgLO;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

// import com.mob.tools.utils.R;


/**
 * Created by Administrator on 2018/3/28
 */
public class HomeAdapter extends BaseAdapter {
    private Context context;//上下文环境，相当于传this
    //一个由数组实现的链表，相当于容器，用来放有多少个Item项，动态添加，长度不是固定的
    private ArrayList<Product> products = new ArrayList<Product>();

    public HomeAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return products.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == view) {//判断是不是空控件
            //获取布局，将xml文件转换成view，即把xml文件当成view来使用
            view = LayoutInflater.from(context).inflate(R.layout.item_detial, null);
        }
        ImageView homeImage = (ImageView) view.findViewById(R.id.home_image);
       homeImage.setImageResource(products.get(i).getmImage());
       // ImgLO.initImageLoader(context);
        // ImageLoader.getInstance().displayImage(products.get(i).getmImage(), homeImage);
        TextView homeTitle = (TextView) view.findViewById(R.id.home_title);
        homeTitle.setText(products.get(i).getmTitle());
        TextView homeAddres = (TextView) view.findViewById(R.id.home_site);
        homeAddres.setText(products.get(i).getmAddress() + "");
        TextView homeContent = (TextView) view.findViewById(R.id.home_content);
        homeContent.setText(products.get(i).getmContent());
        return view;
    }
}
