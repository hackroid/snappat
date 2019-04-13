package com.sustech.snappat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PublishActivity extends AppCompatActivity {

    private EditText mHint;
    private EditText mMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);//注意为“R.layout.activity_second”
        ImageView pic = (ImageView) findViewById(R.id.picture);
        int imageResource = getResources().getIdentifier("@drawable/pic", null, this.getPackageName());
        pic.setImageResource(imageResource);

        //使用findViewById 得到TextView对象
        mHint = (EditText) findViewById(R.id.hint);
        mHint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                toast("您输入的数据为："+s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //使用findViewById 得到mymessage对象
        mMessage = (EditText) findViewById(R.id.mymessage);
        mMessage.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        mMessage.setGravity(Gravity.TOP);
        mMessage.setSingleLine(false);
        mMessage.setHorizontallyScrolling(false);
        mMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                toast("您输入的数据为："+s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void toast(String s){
        Toast.makeText(getApplication(),s,Toast.LENGTH_SHORT).show();
    }

}