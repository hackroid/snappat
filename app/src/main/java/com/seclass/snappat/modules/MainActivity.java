/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseActivity;
import com.seclass.snappat.modules.home.HomeFragment;
import com.seclass.snappat.modules.normal.NormalPresenter;
import com.seclass.snappat.modules.normal.NormalView;
import com.seclass.snappat.modules.notify.NotifyFragment;
import com.seclass.snappat.modules.mine.MineFragment;
import com.seclass.snappat.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<NormalView, NormalPresenter> implements NormalView, View.OnClickListener {

    FrameLayout mContent;
    LinearLayout mLlHome;
    LinearLayout mLlNotify;
    LinearLayout mLlMine;

    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;


    private HomeFragment homeFragment;
    private NotifyFragment notifyFragment;
    private MineFragment mineFragment;

    private long exitTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mLlHome = getId(R.id.ll_home);
        mLlNotify = getId(R.id.ll_notify);
        mLlMine = getId(R.id.ll_mine);

        selectedFragment(0);
        tabSelected(mLlHome);
    }

    private void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (notifyFragment == null) {
                    notifyFragment = new NotifyFragment();
                    transaction.add(R.id.content, notifyFragment);
                } else {
                    transaction.show(notifyFragment);
                }
                break;
            case 2:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.content, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void tabSelected(LinearLayout linearLayout) {
        mLlHome.setSelected(false);
        mLlNotify.setSelected(false);
        mLlMine.setSelected(false);
        linearLayout.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                selectedFragment(0);
                tabSelected(mLlHome);
                break;
            case R.id.ll_notify:
                selectedFragment(1);
                tabSelected(mLlNotify);
                break;
            case R.id.ll_mine:
                selectedFragment(2);
                tabSelected(mLlMine);
                break;
        }
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (notifyFragment != null) {
            transaction.hide(notifyFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void toast(CharSequence s) {

    }

    @Override
    public void showNullLayout() {

    }

    @Override
    public void hideNullLayout() {

    }

    @Override
    public void showErrorLayout() {

    }

    @Override
    public void hideErrorLayout() {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 判断间隔时间 大于2秒就退出应用
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                String msg1 = getString(R.string.drop_two_to_home);
                ToastUtils.showLongToast(msg1);
                // 计算两次返回键按下的时间差
                exitTime = System.currentTimeMillis();
            } else {
                // 返回桌面操作
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
