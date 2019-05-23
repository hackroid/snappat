/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.seclass.snappat.modules.card.CardFragment.OnListFragmentInteractionListener;
import com.seclass.snappat.modules.card.MyCardRecyclerViewAdapter;
import com.seclass.snappat.modules.card.dummy.DummyContent.DummyItem;
import com.seclass.snappat.modules.scan.DetectorActivity;
import java.util.ArrayList;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {

  View rootView;

  @BindView(R.id.iv_scan)
  ImageView scan;

  @BindView(R.id.addNew)
  FloatingActionButton addNew_btn;

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
      goodsEntity.setGoodsName("模拟数据" + i);
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
}
