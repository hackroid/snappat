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

import org.checkerframework.checker.nullness.compatqual.NonNullType;
import org.greenrobot.greendao.annotation.NotNull;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Class {@code PublishActivity} publish mystery activity of this app.
 *
 * <p>After click picture take btn, this activity will appear and help user to publish a new mystery</p>
 * <p>extends {@code BaseActivity} with {@code PublishView} and {@code PublishPresenter} </p>
 * <p>implement {@code PublishView}</p>
 *
 * @author <a href="11611310@mail.sustech.edu.cn">Deyuan Chen</a>
 * @since 2.0
 */

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

    /**
     * change bytes to bitmap.
     * @param b Byte array
     * @return bitmap {@code Bitmap} if b is not empty else null
     * @since 2.0
     */
    public Bitmap Bytes2Bimap(@NotNull byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    /**
     * get layout
     * @return layout
     * @since 3.0
     */
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

    /**
     * init data
     * <p> init img, result and pubish img of this activity</p>\
     * @since 3.0
     */
    @Override
    protected void initData(){
        img = getIntent().getByteArrayExtra("img");
        result = getIntent().getStringArrayExtra("result");
        pub_img.setImageBitmap(Bytes2Bimap(img));
    }

    @Override
    public void initEvent() {

    }

    /**
     * publish btn click event
     * <p> publish new mystery when click publish btn</p>
     * @param v {@code View}
     * @since 3.0
     */
    @OnClick({R.id.publish_btn})
    public void onViewClicked(View v) {
        switch(v.getId()){
            case R.id.publish_btn:
                presenter.addMystery(mhint.getText().toString(), maward.getText().toString(), muser_message.getText().toString(), result);
                break;
        }
    }

    /**
     * publish mystery successfully
     * <p> show a toast with "publish" when publish mystery successfully</p>
     * @param msg {@code JSONObject}
     * @since 3.0
     */
    @Override
    public void postMysterySucc(JSONObject msg){
        try{
            toast("Published!");
            finish();
        }catch(Exception e){
            Log.d("Exception",""+e);
        }
    }

    /**
     * publish mystery failed
     * <p> show a toast with "Errno" when publish mystery faild</p>
     * @param msg {@code CommonResponse<Test>}
     * @since 3.0
     */
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
