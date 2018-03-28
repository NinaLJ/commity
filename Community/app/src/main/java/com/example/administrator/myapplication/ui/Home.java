package com.example.administrator.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.util.Utils;

/**
 * Created by Administrator on 2018/3/27.
 */

public class Home extends Activity {
    private LinearLayout headline;
    private LinearLayout information;
    private LinearLayout recreation;
    private LinearLayout dynamic;
    private LinearLayout myself;
    private InformationFragment mInformationFragment;
    private LinearLayout home_main;
    private HeadlineFragment mHeadlineFragment;
    private RecreationFragment mRecreationFragment;
    private DynamicFragment mDynamicFragment;
    private MyselfFragment mMyselfFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        findViewById();
        setOnclickListerner();
        switch (Utils.flag){
            case 1:
               setHeaflinePage();
                break;
            case 2://
                setInformationPage();
            break;
            case 3:
                setRecerationPage();
                break;
            case 4:
                setDynamicPage();
                break;
            case 5:
                setMyselfPage();
                break;
        }
    }



    private void findViewById() {
        headline = (LinearLayout) findViewById(R.id.headline);
        information=(LinearLayout)findViewById(R.id.information);
        recreation=(LinearLayout)findViewById(R.id.recreation);
        dynamic=(LinearLayout)findViewById(R.id.dynamic);
        myself=(LinearLayout)findViewById(R.id.myself);
        home_main=(LinearLayout)findViewById(R.id.home_main);
    }

    private void setOnclickListerner() {
       MyListener mylistener = new MyListener();
        headline.setOnClickListener(mylistener);
        information.setOnClickListener(mylistener);
        recreation.setOnClickListener(mylistener);
        dynamic.setOnClickListener(mylistener);
        myself.setOnClickListener(mylistener);
    }
    class  MyListener  implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //1、获取一个fragmentmanager对象
            android.app.FragmentManager fm = getFragmentManager();
            //2、获取FragmentTransaction
            android.app.FragmentTransaction transaction = fm.beginTransaction();

            switch (v.getId()) {
                case R.id.headline:
                    if(mHeadlineFragment ==null){
                        mHeadlineFragment =new HeadlineFragment();
                    }
                    transaction.replace(R.id.container, mHeadlineFragment);
                    break;
                case R.id.information:
                    if (mInformationFragment == null) {
                        mInformationFragment = new InformationFragment();
                    }
                    //3、设置页面
                    transaction.replace(R.id.container, mInformationFragment);
                    break;
                case R.id.recreation:
                    if (mRecreationFragment == null) {
                        mRecreationFragment = new RecreationFragment();
                    }
                    //3、设置页面
                    transaction.replace(R.id.container,mRecreationFragment);
                    break;
                case R.id.dynamic:
                    if(mDynamicFragment==null){
                        mDynamicFragment=new DynamicFragment();
                    }
                    transaction.replace(R.id.container,mDynamicFragment);
                    break;
                case R.id.myself:
                    if(mMyselfFragment==null){
                        mMyselfFragment=new MyselfFragment();
                    }
                    transaction.replace(R.id.container,mMyselfFragment);
                    break;
            }
            //4、执行更改
            transaction.commit();

            home_main.invalidate();//更新

        }
    };

    private void setInformationPage() {
        //1、获取一个fragmentmanager对象
        android.app.FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(mInformationFragment == null){
            mInformationFragment = new InformationFragment();
        }
        //3、设置页面
        transaction.replace(R.id.container, mInformationFragment);
        //4、执行更改
        transaction.commit();
        home_main.invalidate();//更新
    }
    private void setHeaflinePage() {
        //1、获取一个fragmentmanager对象
        android.app.FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(mHeadlineFragment == null){
            mHeadlineFragment = new HeadlineFragment();
        }
        //3、设置页面
        transaction.replace(R.id.container, mHeadlineFragment);
        //4、执行更改
        transaction.commit();
        home_main.invalidate();//更新
    }
    private void setRecerationPage() {
        //1、获取一个fragmentmanager对象
        android.app.FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(mRecreationFragment == null){
            mRecreationFragment= new RecreationFragment();
        }
        //3、设置页面
        transaction.replace(R.id.container, mRecreationFragment);
        //4、执行更改
        transaction.commit();
        home_main.invalidate();//更新
    }
    private void  setDynamicPage() {
        //1、获取一个fragmentmanager对象
        android.app.FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(mDynamicFragment == null){
            mDynamicFragment = new DynamicFragment();
        }
        //3、设置页面
        transaction.replace(R.id.container, mDynamicFragment);
        //4、执行更改
        transaction.commit();
        home_main.invalidate();//更新
    }
    private void  setMyselfPage() {
        //1、获取一个fragmentmanager对象
        android.app.FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(mMyselfFragment == null){
            mMyselfFragment = new MyselfFragment();
        }
        //3、设置页面
        transaction.replace(R.id.container, mMyselfFragment);
        //4、执行更改
        transaction.commit();
        home_main.invalidate();//更新
    }


}
