package com.example.administrator.myapplication.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.util.Utils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static java.lang.System.in;

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
    boolean isActive;
    private TextView tv_imge;
    private TextView tv_text;
    private TextView infor_image;
    private TextView infor_text;
    private TextView yule_image;
    private TextView yule_text;
    private TextView dynamic_image;
    private TextView dynamic_text;
    private TextView my_image;
    private TextView my_text;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        findViewById();
        setOnclickListerner();
        switch (Utils.flag) {
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void findViewById() {
        headline = (LinearLayout) findViewById(R.id.headline);
        information = (LinearLayout) findViewById(R.id.information);
        recreation = (LinearLayout) findViewById(R.id.recreation);
        dynamic = (LinearLayout) findViewById(R.id.dynamic);
        myself = (LinearLayout) findViewById(R.id.myself);
        home_main = (LinearLayout) findViewById(R.id.home_main);
        tv_imge = (TextView) findViewById(R.id.head_imag);
        tv_text = (TextView) findViewById(R.id.head_text);
        infor_image = (TextView) findViewById(R.id.infor_image);
        infor_text = (TextView) findViewById(R.id.infor_text);
        yule_image = (TextView) findViewById(R.id.yule_image);
        yule_text = (TextView) findViewById(R.id.yule_text);
        dynamic_image = (TextView) findViewById(R.id.dynamic_image);
        dynamic_text = (TextView) findViewById(R.id.dynamic_text);
        my_image = (TextView) findViewById(R.id.my_image);
        my_text = (TextView) findViewById(R.id.my_text);
    }

    private void setOnclickListerner() {
        MyListener mylistener = new MyListener();
        headline.setOnClickListener(mylistener);
        information.setOnClickListener(mylistener);
        recreation.setOnClickListener(mylistener);
        dynamic.setOnClickListener(mylistener);
        myself.setOnClickListener(mylistener);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Home Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //1、获取一个fragmentmanager对象
            FragmentManager fm = getFragmentManager();
            //2、获取FragmentTransaction
            FragmentTransaction transaction = fm.beginTransaction();

            switch (v.getId()) {
                case R.id.headline:
                    if (mHeadlineFragment == null) {
                        mHeadlineFragment = new HeadlineFragment();
                    }
                    transaction.replace(R.id.container, mHeadlineFragment);
                    if (isActive) {
                        isActive = false;
                    } else {
                        isActive = true;
                        tv_imge.setBackgroundResource(R.mipmap.headline1);
                        infor_image.setBackgroundResource(R.mipmap.information);
                        yule_image.setBackgroundResource(R.mipmap.recreation);
                        dynamic_image.setBackgroundResource(R.mipmap.dynamic);
                        my_image.setBackgroundResource(R.mipmap.myself);
                        tv_text.setTextColor(getResources().getColor(R.color.yello));
                        infor_text.setTextColor(getResources().getColor(R.color.gray));
                        yule_text.setTextColor(getResources().getColor(R.color.gray));
                        dynamic_text.setTextColor(getResources().getColor(R.color.gray));
                        my_text.setTextColor(getResources().getColor(R.color.gray));

                    }
                    break;
                case R.id.information:
                    if (mInformationFragment == null) {
                        mInformationFragment = new InformationFragment();
                    }
                    //3、设置页面
                    transaction.replace(R.id.container, mInformationFragment);
                    if (isActive) {
                        isActive = false;
                    } else {
                        isActive = true;
                        tv_imge.setBackgroundResource(R.mipmap.headline);
                        infor_image.setBackgroundResource(R.mipmap.information1);
                        yule_image.setBackgroundResource(R.mipmap.recreation);
                        dynamic_image.setBackgroundResource(R.mipmap.dynamic);
                        my_image.setBackgroundResource(R.mipmap.myself);
                        tv_text.setTextColor(getResources().getColor(R.color.gray));
                        infor_text.setTextColor(getResources().getColor(R.color.yello));
                        yule_text.setTextColor(getResources().getColor(R.color.gray));
                        dynamic_text.setTextColor(getResources().getColor(R.color.gray));
                        my_text.setTextColor(getResources().getColor(R.color.gray));
                    }
                    break;
                case R.id.recreation:
                    if (mRecreationFragment == null) {
                        mRecreationFragment = new RecreationFragment();
                    }
                    //3、设置页面
                    transaction.replace(R.id.container, mRecreationFragment);
                    if (isActive) {
                        isActive = false;
                        tv_imge.setBackgroundResource(R.mipmap.headline);
                    } else {
                        isActive = true;
                        tv_imge.setBackgroundResource(R.mipmap.headline);
                        infor_image.setBackgroundResource(R.mipmap.information);
                        yule_image.setBackgroundResource(R.mipmap.recreation1);
                        dynamic_image.setBackgroundResource(R.mipmap.dynamic);
                        my_image.setBackgroundResource(R.mipmap.myself);
                        tv_text.setTextColor(getResources().getColor(R.color.gray));
                        infor_text.setTextColor(getResources().getColor(R.color.gray));
                        yule_text.setTextColor(getResources().getColor(R.color.yello));
                        dynamic_text.setTextColor(getResources().getColor(R.color.gray));
                        my_text.setTextColor(getResources().getColor(R.color.gray));
                    }
                    break;
                case R.id.dynamic:
                    if (mDynamicFragment == null) {
                        mDynamicFragment = new DynamicFragment();
                    }
                    transaction.replace(R.id.container, mDynamicFragment);
                    if (isActive) {
                        isActive = false;
                        tv_imge.setBackgroundResource(R.mipmap.headline);
                    } else {
                        isActive = true;
                        tv_imge.setBackgroundResource(R.mipmap.headline);
                        infor_image.setBackgroundResource(R.mipmap.information);
                        yule_image.setBackgroundResource(R.mipmap.recreation);
                        dynamic_image.setBackgroundResource(R.mipmap.dynamic1);
                        my_image.setBackgroundResource(R.mipmap.myself);
                        tv_text.setTextColor(getResources().getColor(R.color.gray));
                        infor_text.setTextColor(getResources().getColor(R.color.gray));
                        yule_text.setTextColor(getResources().getColor(R.color.gray));
                        dynamic_text.setTextColor(getResources().getColor(R.color.yello));
                        my_text.setTextColor(getResources().getColor(R.color.gray));
                    }
                    break;
                case R.id.myself:
                    if (mMyselfFragment == null) {
                        mMyselfFragment = new MyselfFragment();
                    }
                    transaction.replace(R.id.container, mMyselfFragment);
                    if (isActive) {
                        isActive = false;
                    } else {
                        isActive = true;
                        tv_imge.setBackgroundResource(R.mipmap.headline);
                        infor_image.setBackgroundResource(R.mipmap.information);
                        yule_image.setBackgroundResource(R.mipmap.recreation);
                        dynamic_image.setBackgroundResource(R.mipmap.dynamic);
                        my_image.setBackgroundResource(R.mipmap.myself1);
                        tv_text.setTextColor(getResources().getColor(R.color.gray));
                        infor_text.setTextColor(getResources().getColor(R.color.gray));
                        yule_text.setTextColor(getResources().getColor(R.color.gray));
                        dynamic_text.setTextColor(getResources().getColor(R.color.gray));
                        my_text.setTextColor(getResources().getColor(R.color.yello));
                    }
                    break;
            }
            //4、执行更改
            transaction.commit();

            home_main.invalidate();//更新

        }
    }

    ;


    private void setInformationPage() {
        //1、获取一个fragmentmanager对象
        FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction
        FragmentTransaction transaction = fm.beginTransaction();
        if (mInformationFragment == null) {
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
        FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction
        FragmentTransaction transaction = fm.beginTransaction();
        if (mHeadlineFragment == null) {
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
        FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction
        FragmentTransaction transaction = fm.beginTransaction();
        if (mRecreationFragment == null) {
            mRecreationFragment = new RecreationFragment();
        }
        //3、设置页面
        transaction.replace(R.id.container, mRecreationFragment);
        //4、执行更改
        transaction.commit();
        home_main.invalidate();//更新
    }

    private void setDynamicPage() {
        //1、获取一个fragmentmanager对象
        FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction
        FragmentTransaction transaction = fm.beginTransaction();
        if (mDynamicFragment == null) {
            mDynamicFragment = new DynamicFragment();
        }
        //3、设置页面
        transaction.replace(R.id.container, mDynamicFragment);
        //4、执行更改
        transaction.commit();
        home_main.invalidate();//更新
    }

    private void setMyselfPage() {
        //1、获取一个fragmentmanager对象
        FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction
        FragmentTransaction transaction = fm.beginTransaction();
        if (mMyselfFragment == null) {
            mMyselfFragment = new MyselfFragment();
        }
        //3、设置页面
        transaction.replace(R.id.container, mMyselfFragment);
        //4、执行更改
        transaction.commit();
        home_main.invalidate();//更新
    }


}
