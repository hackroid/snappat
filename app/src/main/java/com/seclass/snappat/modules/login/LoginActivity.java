/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.seclass.snappat.R;
import com.seclass.snappat.app.ActivityUtils;
import com.seclass.snappat.base.BaseActivity;
import com.seclass.snappat.bean.CodeAuthBean;
import com.seclass.snappat.modules.MainActivity;
import com.seclass.snappat.utils.RegexUtil;
import com.seclass.snappat.utils.Utils;
import com.seclass.snappat.view.ClearEditText;
import com.seclass.snappat.view.countdowntimer.CountDownTimerUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Class {@code LoginActivity} Login Activity of this app.
 *
 * <p>Login activity of this app. Implements send verify code, receive verify code result,
 * react with verify code result, activity jump and link</p>
 * <p>extends {@code BaseActivity} with {@code BaseActivity} and {@code LoginPresenter} </p>
 * <p>implement {@code LoginView}</p>
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */


public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

  @BindView(R.id.title)
  RelativeLayout mTitle;
  @BindView(R.id.mobile_phone)
  ClearEditText mMobilePhone;
  @BindView(R.id.sms_password)
  ClearEditText mSmsPassword;
  @BindView(R.id.create_account)
  Button mCreatNumber;
  @BindView(R.id.get_code)
  TextView mGetCode;
  @BindView(R.id.go_pocketeos_user)
  TextView mGoPolicyUser;

  @Override
  protected int getLayoutId() {
    return R.layout.activity_login;
  }

  @Override
  public LoginPresenter initPresenter() {
    return new LoginPresenter(LoginActivity.this);
  }

  @Override
  protected void initViews(Bundle savedInstanceState) {

  }

  @Override
  protected void initData() {
    // Debug
    mMobilePhone.setText("13028871392");
  }

  /**
   * init event .
   *
   * <p>init event for get code, go policy user</p>
   *
   * @since 2.0
   */

  @Override
  public void initEvent() {
    mGetCode.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String phonenumber = mMobilePhone.getText().toString().trim();
        if (!TextUtils.isEmpty(phonenumber)) {
          if (RegexUtil.isMobileNO(phonenumber)) {
            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(mGetCode, 60 * 1000,
                1000, "#999999");
            countDownTimerUtils.start();
            presenter.getCodeData(phonenumber);
          } else {
            toast(getString(R.string.phone_format));
          }
        } else {
          toast(getString(R.string.phone_input));
        }
      }
    });
    mGoPolicyUser.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        toast("还没写~~~~");
      }
    });
  }

  /**
   * create account click .
   *
   * <p>create a new account</p>
   *
   * @since 2.0
   */

  @OnClick({R.id.create_account})
  public void onViewClicked(View v) {
    switch (v.getId()) {
      case R.id.create_account:
        if (!TextUtils.isEmpty(mSmsPassword.getText().toString()) && !TextUtils
            .isEmpty(mMobilePhone.getText().toString().trim())) {
          presenter.getcodeAuthData(mMobilePhone.getText().toString().trim(),
              mSmsPassword.getText().toString());
        } else {
          hideProgress();
          toast(getString(R.string.input_all_message));
        }
        break;
      default:
        break;
    }
  }

  @Override
  protected void initImmersionBar() {
    super.initImmersionBar();
    mImmersionBar.fitsSystemWindows(false).statusBarColor(R.color.transparent).titleBar(mTitle)
        .statusBarDarkFont(false, 0f).init();
  }

  @Override
  public void getCodeDataHttp(String msg) {
    toast(msg);
    mSmsPassword.setFocusable(true);
    mSmsPassword.setFocusableInTouchMode(true);
    mSmsPassword.requestFocus();
  }

  /**
   * update user information.
   *
   * @since 2.0
   */

  @Override
  public void getCodeAuthDataHttp(CodeAuthBean.DataBean codeAuthBean) {
    Utils.getSpUtils().put("phone_number", mMobilePhone.getText().toString().trim());
    Utils.getSpUtils().put("uid", codeAuthBean.getUid());
    Utils.getSpUtils().put("user_name", mMobilePhone.getText().toString().trim());
    Log.d("Debug", "getCodeAuthDataHttp: " + codeAuthBean.getUid());

    hideProgress();

    ActivityUtils.next(LoginActivity.this, MainActivity.class);
  }

  @Override
  public void getDataHttpFail(String msg) {
    hideProgress();
    toast(msg);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    hideProgress();
  }

}
