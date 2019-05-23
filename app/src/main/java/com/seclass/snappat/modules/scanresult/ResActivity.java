/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.scanresult;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseActivity;
import com.seclass.snappat.utils.ToastUtils;

import butterknife.BindView;


public class ResActivity extends BaseActivity<ResView, ResPresenter> implements ResView {

    @BindView(R.id.scan_img_result)
    ImageView pub_img;

    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan_result;
    }

    @Override
    public ResPresenter initPresenter() {
        return new ResPresenter(ResActivity.this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        byte[] img = getIntent().getByteArrayExtra("img");
        String[] result = getIntent().getStringArrayExtra("result");

        StringBuilder sb = new StringBuilder();
        for (String i : result) {
            sb.append(i);
            sb.append("~");
        }
        ToastUtils.showShortToast(sb.toString());
        pub_img.setImageBitmap(Bytes2Bimap(img));
    }

    @Override
    public void initEvent() {

    }

}