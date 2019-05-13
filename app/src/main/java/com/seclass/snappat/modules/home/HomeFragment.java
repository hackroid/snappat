/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseFragment;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {

  LinearLayout homeFriends;
  LinearLayout homeLatest;
  LinearLayout homeHot;
  LinearLayout homoeReward;

  Unbinder unbinder;

  @Override
  public HomePresenter initPresenter() {
    return new HomePresenter(getActivity());
  }

  @Override
  protected void initViews(Bundle savedInstanceState) {}

  @Override
  protected void initData() {}

  @Override
  public void initEvent() {}

  @Override
  protected int getContentViewLayoutID() {
    return R.layout.fragment_home;
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
