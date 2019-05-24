/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.seclass.snappat.R;
import com.seclass.snappat.app.ActivityUtils;
import com.seclass.snappat.base.BaseFragment;
import com.seclass.snappat.bean.CommonResponse;
import com.seclass.snappat.bean.CommonResponse.Test;
import com.seclass.snappat.modules.scan.DetectorActivity;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {

  View rootView;

  @BindView(R.id.iv_scan)
  ImageView scan;

  @BindView(R.id.addNew)
  FloatingActionButton addNew_btn;

  @BindView(R.id.homePullToRefresh)
  SwipeRefreshLayout pullToRefresh;

  Unbinder unbinder;
  public RecyclerView mCollectRecyclerView; // 定义RecyclerView
  // 定义以goodsentity实体类为对象的数据集合
  private ArrayList<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();
  // 自定义recyclerveiw的适配器
  private CollectRecycleAdapter mCollectRecyclerAdapter;

  @Override
  public HomePresenter initPresenter() {
    return new HomePresenter(getActivity());
  }

  @Override
  protected void initViews(Bundle savedInstanceState) {}

  @Override
  protected void initData() {
    for (int i = 0; i < 10; i++) {
      GoodsEntity goodsEntity = new GoodsEntity();
      goodsEntity.setGoodsHint("模拟数据" + i);
      goodsEntity.setGoodsPrice("100" + i);
      goodsEntityList.add(goodsEntity);
    }
  }

  private void initRecyclerView() {
    // 获取RecyclerView
    mCollectRecyclerView = (RecyclerView) rootView.findViewById(R.id.puzzle_list);
    // 创建adapter
    mCollectRecyclerAdapter = new CollectRecycleAdapter(getActivity(), goodsEntityList);
    // 给RecyclerView设置adapter
    mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
    // 设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
    // 参数是：上下文、列表方向（横向还是纵向）、是否倒叙
    mCollectRecyclerView.setLayoutManager(
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    // 设置item的分割线
    mCollectRecyclerView.addItemDecoration(
        new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    // RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
    mCollectRecyclerAdapter.setOnItemClickListener(
        new CollectRecycleAdapter.OnItemClickListener() {
          @Override
          public void OnItemClick(View view, GoodsEntity data) {
            // 此处进行监听事件的业务处理
            Toast.makeText(getActivity(), "我是item", Toast.LENGTH_SHORT).show();
          }
        });
  }

  @Override
  public void initEvent() {
    scan.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isScan", true);
            ActivityUtils.next(getActivity(), DetectorActivity.class, bundle);
          }
        });

    addNew_btn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isScan", false);
            ActivityUtils.next(getActivity(), DetectorActivity.class, bundle);
          }
        });
    Log.d("info", "========Start getting puzzle=====");
    presenter.getPuzzleInfo();
    Log.d("info", "========End getting puzzle======");
    pullToRefresh.setOnRefreshListener(
        new OnRefreshListener() {
          @Override
          public void onRefresh() {
            presenter.getPuzzleInfo();
            pullToRefresh.setRefreshing(false);
          }
        });
  }

  @Override
  protected int getContentViewLayoutID() {
    return R.layout.fragment_home;
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    rootView = super.onCreateView(inflater, container, savedInstanceState);
    assert rootView != null;
    unbinder = ButterKnife.bind(this, rootView);
    initRecyclerView();
    initData();
    return rootView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public void getPuzzleSucc(JSONArray msg) {
    try {
      goodsEntityList.clear();
      for (int i = 0; i < msg.length(); i++) {
        Log.d("Debug", "getPuzzleSucc" + i);
        JSONObject goodsEntityObject = msg.getJSONObject(i);
        Log.d("Debug", "getPuzzleSucc" + goodsEntityObject.toString());
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodsHint(goodsEntityObject.getString("hint"));
        goodsEntity.setGoodsPrice(goodsEntityObject.getString("coins"));
        goodsEntity.setgoodsUsername(goodsEntityObject.getString("userid"));
        goodsEntity.setGoodsTreasure(goodsEntityObject.getString("treasure"));
        goodsEntityList.add(goodsEntity);
      }
      mCollectRecyclerAdapter.notifyDataChanged(getActivity(), goodsEntityList);
      // 成功
      //      String userid = msg.getString("userid");
      //      String hint = msg.getString("hint");
      //      String coins = msg.getString("coins");
      //
      //      if(des.length()==0){
      //        des="这个人很懒,什么都没写";
      //      }
      //      String c = msg.getString("coins");
      //      String fl = msg.getString("follower");

      //      if(fl.equals("[]")){
      //        fl="";
      //      }
      //      userText.setText("用户名: "+username);
      //      Log.d("debug-username", username);
      //      phone.setText("电话: "+phone_number);
      //      description.setText("简介:"+des);
      //      coin.setText("金币:"+c);
      //      follower.setText("关注你的人:"+fl.length());
      Log.d("info:", "getPuzzleSucc" + goodsEntityList.toString());
    } catch (Exception e) {
      Log.d("Exception", "getPuzzleSucc" + e);
    }
  }

  @Override
  public void getPuzzleFail(CommonResponse<Test> msg) {
    if (msg.errno != 0) {
      if (msg.errno == 1003) {}
    }
    hideProgress();
    toast(msg.toString());
  }
}
