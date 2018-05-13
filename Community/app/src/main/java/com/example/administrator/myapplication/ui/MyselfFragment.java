package com.example.administrator.myapplication.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import static com.example.administrator.myapplication.ui.Login.bitmap;
import static com.example.administrator.myapplication.ui.Login.mQQAuth;

/**
 * Created by Administrator on 2018/3/28.
 */

public class MyselfFragment extends Fragment {
    private View view ;
    private TextView peson;
    private LinearLayout help;
    private LinearLayout my_collect;
    private LinearLayout my_about;
    private ImageView my_image;
    private TextView btn_out;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_my,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViewById();
        setonclickLister();
        updateLoginButton();

    }

    private void findViewById() {
        peson=(TextView)view.findViewById(R.id.pesonal);
        help=(LinearLayout)view.findViewById(R.id.feedbaxk);
        my_collect=(LinearLayout)view.findViewById(R.id.collect);
        my_about=(LinearLayout)view.findViewById(R.id.about_my);
        my_image=(ImageView)view.findViewById(R.id.IvPersonImg);
        btn_out=(TextView)view.findViewById(R.id.Btn_LogOut);

    }
    private void setonclickLister() {
        peson.setOnClickListener(myLister);
        help.setOnClickListener(myLister);
        my_collect.setOnClickListener(myLister);
        my_about.setOnClickListener(myLister);
        btn_out.setOnClickListener(myLister);
    }
    private void updateLoginButton() {
        if( Login.isQQLogined){
            my_image.setImageBitmap(bitmap);
        }
    }
    private void signOut() {
        // if( Login.isQQLogined ){//如果qq登录则需要退出
        //    new Login().Logout();

        //  }
        if( mQQAuth != null && mQQAuth.isSessionValid()){//如果qq登录则需要退出

        }else {
            new Login().Logout();
        }
                startActivity(new Intent(getActivity(),Login.class));



    }

    View.OnClickListener myLister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.pesonal:
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), Personal.class);
                    startActivity(intent);
                    break;
                case R.id.feedbaxk:
                    Intent intent1 = new Intent();
                    intent1.setClass(getActivity(), Help.class);
                    startActivity(intent1);
                    break;
                case R.id.collect:
                    Intent intent2 = new Intent();
                    intent2.setClass(getActivity(), MyCollection.class);
                    startActivity(intent2);
                    break;
                case R.id.about_my:
                    Intent intent3 = new Intent();
                    intent3.setClass(getActivity(), Aboutus.class);
                    startActivity(intent3);
                    break;
                case R.id.Btn_LogOut:
                    signOut();
                    break;

            }
        }

    };

}
