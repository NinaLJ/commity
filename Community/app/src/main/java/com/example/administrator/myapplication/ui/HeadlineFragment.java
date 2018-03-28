package com.example.administrator.myapplication.ui;

import android.app.Activity;
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
import com.example.administrator.myapplication.entity.Product;

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
        setonclickLister();
        mHomeAdapter = new HomeAdapter(getActivity(), ls);//Utils.ls
        lv.setAdapter(mHomeAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private void findview() {
        water = (LinearLayout) view.findViewById(R.id.water);
        electric = (LinearLayout) view.findViewById(R.id.electric);
        fuelgas = (LinearLayout) view.findViewById(R.id.fuelgas);
        lv=(ListView)view.findViewById(R.id.lv);
    }

    private void setonclickLister() {
        water.setOnClickListener(myLister);
        electric.setOnClickListener(myLister);
        fuelgas.setOnClickListener(myLister);
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
            }
        }

    };

    //网络请求数据
    private void getDate() {
        ls.add(new Product(1, R.mipmap.loading, "新款迷你贝壳包拉链包拉链条包手机小包 ", "hahahhahhhhh", "河北师范大学"));
        ls.add(new Product(2, R.mipmap.loading, "新款迷你贝壳包拉链包拉链条包手机小包1 ", "hahahhahhhhh", "河北师范大学"));
        ls.add(new Product(3, R.mipmap.loading, "新款迷你贝壳包拉链包拉链条包手机小包2 ", "hahahhahhhhh", "河北师范大学"));
        ls.add(new Product(4, R.mipmap.loading, "新款迷你贝壳包拉链包拉链条包手机小包3 ", "hahahhahhhhh", "河北师范大学"));
        ls.add(new Product(5, R.mipmap.loading, "新款迷你贝壳包拉链包拉链条包手机小包4 ", "hahahhahhhhh", "河北师范大学"));
    }
}
