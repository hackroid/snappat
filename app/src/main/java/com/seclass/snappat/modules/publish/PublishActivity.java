package com.seclass.snappat.modules.publish;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseActivity;

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

    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.publish_btn})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.publish_btn:
                    String message = muser_message.getText().toString().trim();
                    String award = maward.getText().toString().trim();
                    String hint = mhint.getText().toString().trim();
                    presenter.getMesteryData(hint, award, message);

                    break;
            default:
                break;
        }
    }

}
