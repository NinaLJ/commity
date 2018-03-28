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

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.HomeAdapter;
import com.example.administrator.myapplication.adapter.InformationAdapter;
import com.example.administrator.myapplication.entity.Information;
import com.example.administrator.myapplication.entity.Product;

import java.util.ArrayList;

import static com.example.administrator.myapplication.R.id.lv;

public class InformationFragment extends Fragment {
    private View view;
    private LinearLayout back;
    private ListView lv_information;
    private ArrayList<Information> ls_infor = new ArrayList<Information>();
    private InformationAdapter mInformationAdapter;

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
        getDate();
        setListener();
        mInformationAdapter = new InformationAdapter(getActivity(), ls_infor);//Utils.ls
        lv_information.setAdapter(mInformationAdapter);
        lv_information.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void getview(){
        back=(LinearLayout)view.findViewById(R.id.back);
        lv_information=(ListView)view.findViewById(R.id.lv_information);

    }
    private void setListener(){
        back.setOnClickListener(myListener);

    }
    private View.OnClickListener myListener = new View.OnClickListener() {
        Intent i = new Intent();
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    i.setClass(getActivity(), Home.class);
                    break;
            }
            startActivity(i);
        }
    };
    //网络请求数据
    private void getDate() {
        ls_infor.add(new Information(1, R.mipmap.loading, "新款迷你贝壳包拉链包拉链条包手机小包 ", "hahahhahhhhh", "2018-3-29"));
        ls_infor.add(new Information(2, R.mipmap.loading, "新款迷你贝壳包拉链包拉链条包手机小包1 ", "hahahhahhhhh", "2018-3-29"));
        ls_infor.add(new Information(3, R.mipmap.loading, "新款迷你贝壳包拉链包拉链条包手机小包2 ", "hahahhahhhhh", "2018-3-29"));
        ls_infor.add(new Information(4, R.mipmap.loading, "新款迷你贝壳包拉链包拉链条包手机小包3 ", "hahahhahhhhh", "2018-3-29"));
        ls_infor.add(new Information(5, R.mipmap.loading, "新款迷你贝壳包拉链包拉链条包手机小包4 ", "hahahhahhhhh", "2018-3-29"));
    }

}
