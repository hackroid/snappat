/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.scanresult;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.seclass.snappat.R;
import com.seclass.snappat.app.ActivityUtils;
import com.seclass.snappat.base.BaseActivity;
import com.seclass.snappat.utils.ToastUtils;

import java.util.Arrays;

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
    @BindView(R.id.scan_rtn_btn)
    Button rtn_btn;
    @BindView(R.id.scan_result_str)
    TextView scan_result_text;
    @BindView(R.id.award_result_str)
    TextView award_result_text;
    @BindView(R.id.back_imageView)
    ImageView back_img;
    @BindView(R.id.treasure_result_str)
    TextView treasure_result_str;
    String[] result;
    String treasure;
    String coins;
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
        result = getIntent().getStringArrayExtra("result");
        treasure = getIntent().getStringExtra("treasure");
        coins = getIntent().getStringExtra("coins");

        StringBuilder sb = new StringBuilder();
        if(result!=null){
            for (String i : result) {
                sb.append(i);
                sb.append("~");
            }
            ToastUtils.showShortToast(sb.toString()+"\n"+treasure+"\n"+coins);
            pub_img.setImageBitmap(Bytes2Bimap(img));
            scan_result_text.setText("扫描结果: "+ Arrays.toString(result));
            award_result_text.setText("获得金币: "+ coins +" coins");
            treasure_result_str.setText("对方给你的留言：" + treasure);
        }

    }

    @Override
    public void initEvent() {
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rtn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
