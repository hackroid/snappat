/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.seclass.snappat.R;
import com.seclass.snappat.app.ActivityUtils;
import com.seclass.snappat.base.BaseFragment;
import com.seclass.snappat.modules.MainActivity;
import com.seclass.snappat.modules.publish.PublishActivity;
import com.seclass.snappat.modules.scan.DetectorActivity;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.iv_scan)
    ImageView scan;
//    LinearLayout homeFriends;
//    LinearLayout homeLatest;
//    LinearLayout homeHot;
//    LinearLayout homoeReward;
    @BindView(R.id.addNew)
    FloatingActionButton addNew_btn;

    Unbinder unbinder;

    @BindView(R.id.pub_btn)
    Button publish_button;
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
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("isScan", true);
                ActivityUtils.next(getActivity(), DetectorActivity.class, bundle);
            }
        });

        addNew_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("isScan", false);
                ActivityUtils.next(getActivity(), DetectorActivity.class, bundle);
            }
        });
        publish_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                ActivityUtils.next(getActivity(), PublishActivity.class, bundle);
            }
        });
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
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
