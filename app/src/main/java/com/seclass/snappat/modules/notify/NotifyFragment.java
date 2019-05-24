/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.notify;

import android.os.Bundle;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseFragment;
import com.seclass.snappat.bean.ResponseBean;
import java.util.ArrayList;

/**
 * class {@code NotifyFragment}.
 *
 * <p>base notify fragment.
 *
 * <p>all implements
 *
 * <p>{@link NotifyView}
 *
 * <p>extends {@link BaseFragment<NotifyView, NotifyPresenter>}
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */
public class NotifyFragment extends BaseFragment<NotifyView, NotifyPresenter>
    implements NotifyView {

  View rootView;
  Unbinder unbinder;
  public RecyclerView mMsgRecyclerView;
  private ArrayList<MessageEntity> messageEntityList = new ArrayList<MessageEntity>();
  private MessageRecycleAdapter mMessageRecycleAdapter;

  @Override
  public NotifyPresenter initPresenter() {
    return new NotifyPresenter(getActivity());
  }

  @Override
  protected void initViews(Bundle savedInstanceState) {}

  @Override
  protected void initData() {
    for (int i = 0; i < 10; i++) {
      MessageEntity messageEntity = new MessageEntity();
      messageEntity.setMsgComment("模拟Msg comment " + i);
      messageEntity.setMsgSender("用户" + i);
      messageEntity.setMsgType(i % 3);
      messageEntity.setMsgRead(i % 2);
      messageEntityList.add(messageEntity);
    }
  }

  private void initRecyclerView() {
    mMsgRecyclerView = (RecyclerView) rootView.findViewById(R.id.message_list);
    mMessageRecycleAdapter = new MessageRecycleAdapter(getActivity(), messageEntityList);
    mMsgRecyclerView.setAdapter(mMessageRecycleAdapter);
    mMsgRecyclerView.setLayoutManager(
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    mMsgRecyclerView.addItemDecoration(
        new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    mMessageRecycleAdapter.setOnItemClickListener(
        new MessageRecycleAdapter.OnItemClickListener() {
          @Override
          public void OnItemClick(View view, MessageEntity data) {
            // 此处进行监听事件的业务处理
            Toast.makeText(getActivity(), "我是item", Toast.LENGTH_SHORT).show();
          }
        });
  }

  @Override
  public void initEvent() {}

  @Override
  protected int getContentViewLayoutID() {
    return R.layout.fragment_notify;
  }

  @Override
  public ResponseBean getNotify() {
    return null;
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
