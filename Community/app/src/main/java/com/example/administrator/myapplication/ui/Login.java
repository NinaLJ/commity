package com.example.administrator.myapplication.ui;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.util.AppConstant;
import com.example.administrator.myapplication.util.Util;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;


import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;


public class Login extends Activity {
    private Button Btn;
    private TextView Tex;
    private Button Btnqq;
    public Tencent mTencent;
    public static QQAuth mQQAuth;
    public static String mAppid;
    public static String openidString;//qq的openid
    public static String nicknameString;//qq用户昵称
    public static String TAG = "Login";
    public static Bitmap bitmap = null;//qq头像
    public static String mUserName = "";
    public static String mUserId = "";

    public static boolean isQQLogined = false;
    Intent intent;
    ProgressDialog dialog = null;
    Util util;
    private EditText userName;
    private EditText passWord;

    // 弹出框
    private ProgressDialog mDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_login);
        dialog = new ProgressDialog(Login.this);
        findViewById();
        setOnclickLister();
    }

    private void findViewById() {
        Btn = (Button) findViewById(R.id.loginhome);
        Tex = (TextView) findViewById(R.id.regist);
        Btnqq = (Button) findViewById(R.id.new_login_btn);
        userName=(EditText)findViewById(R.id.login_user);
        passWord=(EditText)findViewById(R.id.login_password);

    }
    private void setOnclickLister() {
        Btn.setOnClickListener(button);
        Tex.setOnClickListener(button);
        Btnqq.setOnClickListener(button);
    }
            View.OnClickListener button=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.loginhome:
                    LoginUser();

                    break;
                case R.id.regist:
                    Intent j = new Intent(Login.this, Regist.class);
                    startActivity(j);
                    break;
                case R.id.new_login_btn:
                    LoginQQ();
//                    Login.this.finish();
                   // intent= new Intent(Login.this, Home.class);
                    break;
            }

        }
    };
    // 这里是调用QQ登录的关键代码
    public void LoginQQ() {
        mAppid = AppConstant.APP_ID;
        mTencent = Tencent.createInstance(mAppid, getApplicationContext());
        if (!mTencent.isSessionValid())
        {
            mTencent.login(Login.this, "all", new BaseUiListener());
        }


    }
    //注销时的代码
    public void Logout() {
        mTencent.logout(Login.this);
    }
    //qq登录成功
    private class BaseUiListener implements IUiListener {

        public void onComplete(Object response) {
          // intent= new Intent(Login.this, Home.class);
            dialog.setMessage("正在登录");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
             dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.show();
             Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            // TODO Auto-generated method stub
            try {
                Log.e(TAG, "-------------" + response.toString());
                openidString = ((JSONObject) response).getString("openid").toLowerCase();
                //openidTextView.setText(openidString);
                Log.e(TAG, "-------------openidString是" + openidString);
                // access_token= ((JSONObject)
                // response).getString("access_token"); //expires_in =
                // ((JSONObject) response).getString("expires_in");
                //应该先做环信登录的操作，再在app服务器登录
                user();//能同步得到用户的昵称和头像吗
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            startActivity(new Intent(Login.this,Home.class));

        }

        public void onCancel() {
            // TODO Auto-generated method stub
        }

        public void onError(UiError arg0) {
            // TODO Auto-generated method stub

        }

    }
    //获得qq登录后的用户昵称和头像
    private void user(){
        QQToken qqToken = mTencent.getQQToken();
        UserInfo info = new UserInfo(getApplicationContext(), qqToken);
        info.getUserInfo(new IUiListener() {
            public void onComplete(final Object response) {
                // TODO Auto-generated method stub
                Log.e(TAG, "----获得昵称");
                Message msg = new Message();
                msg.obj = response;
                msg.what = 0;
                mHandler.sendMessage(msg);
                //nicknameString = response.toString();
                Log.e(TAG, "-----昵称---" + response.toString());

                new Thread() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        JSONObject json = (JSONObject) response;
                        try {
                            bitmap = util.getbitmap(json//Util.getbitmap
                                    .getString("figureurl_qq_2"));
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        Message msg = new Message();
                        msg.obj = bitmap;
                        msg.what = 1;

                        mHandler.sendMessage(msg);
//							customView.setVisibility(View.INVISIBLE);
                    }
                }.start();
            }

            public void onCancel() {
                Log.e(TAG, "--------------111112");
                // TODO Auto-generated method stub
            }

            public void onError(UiError arg0) {
                // TODO Auto-generated method stub
                Log.e(TAG, "-111113" + ":" + arg0);
            }
        });
    }
    private void LoginUser(){
        //取得用户输入的账号和密码
        String name=userName.getText().toString();
        String pass=passWord.getText().toString();
        if( name.equals("")){
            Toast.makeText(Login.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
        }else if( pass.equals("") ){
            Toast.makeText(Login.this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }else if(name.equals("15232126992")&&pass.equals("123456")){
            startActivity(new Intent(Login.this,Home.class));
        }
        else{
            Toast.makeText(Login.this,"用户名密码错误",Toast.LENGTH_SHORT).show();
        }

    }
    //处理昵称、头像线程
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;
                if (response.has("nickname")) {
                    try {
                        nicknameString = response.getString("nickname");
                        // nicknameTextView.setText(nicknameString);
                        Log.e(TAG, "--最后获得的昵称是" + nicknameString);
                        if( !openidString.equals("")){
                            mUserName = openidString;
                            //user();//能同步得到用户的昵称和头像吗
                           // qqRegister();//登录app的服务器
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else if (msg.what == 1) {
                bitmap = (Bitmap) msg.obj;//头像是用的新起的线程，会和主线程不同步吗？会获取到的不是最新的头像吗？
                // userlogo.setImageBitmap(bitmap);
            }
        }

    };
}