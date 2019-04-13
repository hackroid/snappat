package com.sustech.snappat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.widget.ImageView;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

    private ImageView imgV;//初始化控件
    private int time=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgV = (ImageView) findViewById(R.id.welcome_Img);
        imgV.setOnClickListener(new View.OnClickListener() {//创建监听器
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        getSharedPreferences 记录状态
//        SharedPreferences sh = getSharedPreferences("name", MODE_PRIVATE);
//        SharedPreferences.Editor edit = sh.edit();
//        boolean aTrue = sh.getBoolean("true", false);
//        edit.putBoolean("true",true);
//        edit.commit();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time--;
                if (time==0){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 0, 2000);


    }
}
