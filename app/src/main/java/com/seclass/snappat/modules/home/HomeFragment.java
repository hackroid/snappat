/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.home;

import android.os.Bundle;

import com.seclass.snappat.base.BaseFragment;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }


}
