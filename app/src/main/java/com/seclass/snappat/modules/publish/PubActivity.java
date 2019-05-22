/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.publish;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseActivity;

import butterknife.BindView;


public class PubActivity extends BaseActivity<PubView, PubPresenter> implements PubView {

//    @BindView(R.id.pub_img_view)
//    ImageView pub_img;

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
    public PubPresenter initPresenter() {
        return new PubPresenter(PubActivity.this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        byte[] img = getIntent().getByteArrayExtra("img");
        String[] result = getIntent().getStringArrayExtra("result");
        pub_img.setImageBitmap(Bytes2Bimap(img));
    }

    @Override
    public void initEvent() {

    }

}
