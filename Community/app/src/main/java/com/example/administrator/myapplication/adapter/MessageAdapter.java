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
import com.example.administrator.myapplication.entity.Message;

import java.util.ArrayList;

// import com.mob.tools.utils.R;


/**
 * Created by Administrator on 2018/3/28
 */
public class MessageAdapter extends BaseAdapter {
    private Context context;//上下文环境，相当于传this
    //一个由数组实现的链表，相当于容器，用来放有多少个Item项，动态添加，长度不是固定的
    private ArrayList<Message> message= new ArrayList<Message>();

    public MessageAdapter(Context context, ArrayList<Message> message) {
        this.context = context;
        this.message=message;
    }

    @Override
    public int getCount() {
        return message.size();
    }

    @Override
    public Object getItem(int i) {
        return message.get(i);
    }

    @Override
    public long getItemId(int i) {
        return message.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == view) {//判断是不是空控件
            //获取布局，将xml文件转换成view，即把xml文件当成view来使用
            view = LayoutInflater.from(context).inflate(R.layout.zixun_detial, null);
        }
        TextView messageTitle = (TextView) view.findViewById(R.id.message_title);
        messageTitle.setText(message.get(i).getmTitle());
        TextView messageTime = (TextView) view.findViewById(R.id.message_site);
        messageTime.setText(message.get(i).getmTime() + "");
        TextView messageContent = (TextView) view.findViewById(R.id.message_content);
        messageContent.setText(message.get(i).getmContent());
        return view;
    }
}
