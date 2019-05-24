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

/**
 * class {@code ResActivity}.
 *
 * <p>scan result activity.</p>
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */
public class ResActivity extends BaseActivity<ResView, ResPresenter> implements ResView {

    @BindView(R.id.scan_img_result)
    ImageView pub_img;

    /**
     * change bytes to bitmap.
     * @param b Byte array
     * @return bitmap {@code Bitmap} if b is not empty else null
     * @since 2.0
     */
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
        String treasure = getIntent().getStringExtra("treasure");
        String coins = getIntent().getStringExtra("coins");

        StringBuilder sb = new StringBuilder();
        if(result!=null){
            for (String i : result) {
                sb.append(i);
                sb.append("~");
            }
            ToastUtils.showShortToast(sb.toString()+"\n"+treasure+"\n"+coins);
            pub_img.setImageBitmap(Bytes2Bimap(img));
        }

    }

    @Override
    public void initEvent() {

    }

}
