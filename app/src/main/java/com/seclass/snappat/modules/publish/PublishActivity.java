package com.seclass.snappat.modules.publish;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseActivity;
import com.seclass.snappat.bean.CommonResponse;
import com.seclass.snappat.bean.CommonResponse.Test;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class PublishActivity extends BaseActivity<PublishView, PublishPresenter> implements PublishView {

    @BindView(R.id.hint_text)
    EditText mhint;
    @BindView(R.id.award_value)
    EditText maward;
    @BindView(R.id.user_message)
    EditText muser_message;
    @BindView(R.id.publish_btn)
    Button mpub_btn;
    @BindView(R.id.pub_img_view)
    ImageView pub_img;

    String[] result={};
    byte[] img;

    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    public PublishPresenter initPresenter() {
        return new PublishPresenter(PublishActivity.this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(){
        img = getIntent().getByteArrayExtra("img");
        result = getIntent().getStringArrayExtra("result");
        pub_img.setImageBitmap(Bytes2Bimap(img));
    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.publish_btn})
    public void onViewClicked(View v) {
        switch(v.getId()){
            case R.id.publish_btn:
                presenter.addMystery(mhint.getText().toString(), maward.getText().toString(), muser_message.getText().toString(), result);
                break;
        }
    }

    @Override
    public void postMysterySucc(JSONObject msg){
        try{
            toast("Published!");
            finish();
        }catch(Exception e){
            Log.d("Exception",""+e);
        }
    }

    @Override
    public void postMysteryFail(CommonResponse<Test> msg){
        if(msg.errno!=0){
            Log.d("Errno","Errno when getUserInfo"+msg.errmsg);
            if(msg.errno==1003){
                //重新注册一下
            }
        }
        hideProgress();
        toast(msg.toString());

    }
}
