/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.home;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {
    @BindView(R.id.home_tv)
    TextView home_tv;

    Unbinder unbinder;
    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter(getActivity());
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
        return R.layout.fragment_home;
    }

    @OnClick({R.id.home_tv, R.id.home_title1})
    public void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.home_tv:
                home_tv.setText("changed");
                break;
            case R.id.home_title1:
                toast("home title1 clicked");
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
