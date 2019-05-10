package com.seclass.snappat.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.seclass.snappat.utils.ToastUtils;

import butterknife.ButterKnife;

public abstract class BaseFragment<V, P extends BasePresent<V>> extends Fragment implements BaseView {

    protected P presenter;
    protected ImmersionBar mImmersionBar;
    private View mView;  //缓存Fragment view

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        //创建presenter
        presenter = initPresenter();
        initViews(savedInstanceState);
        initData();
        initEvent();
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // 在Activity中初始化P，并且连接V
        presenter.attach((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在onDestroy()生命周期中释放P中引用的V。
        presenter.detach();
        //在onDestroy()生命周期中取消所有子线程里面的网络连接。
        OkGo.getInstance().cancelTag(getActivity());
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    public abstract P initPresenter();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initData();

    public abstract void initEvent();

    /**
     * 是否可以使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return Constants.isImmersionBarEnabled;
    }

    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).statusBarDarkFont(true, 0.2f).init();
    }

    protected abstract int getContentViewLayoutID();

    protected <T extends View> T getId(int id) {
        return (T) mView.findViewById(id);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void toast(CharSequence s) {
        ToastUtils.showShortToast(s);
    }

    @Override
    public void showNullLayout() {

    }

    @Override
    public void hideNullLayout() {

    }

    @Override
    public void showErrorLayout() {
      /*  getId(R.id.error_text).setVisibility(View.VISIBLE);
        getId(R.id.error_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });*/
    }

    @Override
    public void hideErrorLayout() {
//        getId(R.id.error_text).setVisibility(View.GONE);
    }
}