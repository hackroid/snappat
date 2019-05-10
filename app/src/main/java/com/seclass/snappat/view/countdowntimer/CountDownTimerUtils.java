/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.view.countdowntimer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;
    String img_id;

    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval, String img_id) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
        this.img_id=img_id;
    }
    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText((millisUntilFinished/1000)+"s");  //设置倒计时时间
        mTextView.setTextColor(Color.parseColor("#999999"));
    }

    @Override
    public void onFinish() {
        mTextView.setText("获取验证码");
        mTextView.setClickable(true);//重新获得点击
       mTextView.setTextColor(Color.parseColor(img_id));
        cancel();
    }
    public void onMyStop() {
        mTextView.setText("获取验证码");
        mTextView.setClickable(true);//重新获得点击
        mTextView.setTextColor(Color.parseColor("#ff3d46"));
        cancel();
    }
}
