package com.seclass.snappat.modules.notify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.seclass.snappat.R;
import com.seclass.snappat.modules.home.GoodsEntity;
import java.util.ArrayList;

public class MessageRecycleAdapter
    extends RecyclerView.Adapter<MessageRecycleAdapter.myViewHolder> {
  private Context context;
  private ArrayList<MessageEntity> messageEntityList;

  // 创建构造函数
  public MessageRecycleAdapter(Context context, ArrayList<MessageEntity> messageEntityList) {
    // 将传递过来的数据，赋值给本地变量
    this.context = context; // 上下文
    this.messageEntityList = messageEntityList; // 实体类数据ArrayList
  }

  /**
   * 创建viewholder，相当于listview中getview中的创建view和viewhodler
   *
   * @param parent
   * @param viewType
   * @return
   */
  @Override
  public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    // 创建自定义布局
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_msg, parent, false);
    //    View itemView = View.inflate(context, R.layout.fragment_card, null);
    return new myViewHolder(itemView);
  }

  /**
   * 绑定数据，数据与view绑定
   *
   * @param holder
   * @param position
   */
  @Override
  public void onBindViewHolder(myViewHolder holder, int position) {
    // 根据点击位置绑定数据
    MessageEntity data = messageEntityList.get(position);
    //        holder.mItemGoodsImg;
    holder.mItemMsgComment.setText(data.msgComment); // 获取实体类中的name字段并设置
    holder.mItemMsgSender.setText(data.msgSender); // 获取实体类中的price字段并设置
  }

  /**
   * 得到总条数
   *
   * @return
   */
  @Override
  public int getItemCount() {
    return messageEntityList.size();
  }

  // 自定义viewholder
  class myViewHolder extends RecyclerView.ViewHolder {
    private TextView mItemMsgType;
    private TextView mItemMsgComment;
    private TextView mItemMsgSender;
    private TextView mItemMsgRead;

    public myViewHolder(View itemView) {
      super(itemView);
      mItemMsgSender = (TextView) itemView.findViewById(R.id.msg_username);
      mItemMsgComment = (TextView) itemView.findViewById(R.id.msg_content);
      // 点击事件放在adapter中使用，也可以写个接口在activity中调用
      // 方法一：在adapter中设置点击事件
      itemView.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // 可以选择直接在本位置直接写业务处理
              // Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
              // 此处回传点击监听事件
              if (onItemClickListener != null) {
                onItemClickListener.OnItemClick(v, messageEntityList.get(getLayoutPosition()));
              }
            }
          });
    }
  }

  /** 设置item的监听事件的接口 */
  public interface OnItemClickListener {
    /**
     * 接口中的点击每一项的实现方法，参数自己定义
     *
     * @param view 点击的item的视图
     * @param data 点击的item的数据
     */
    public void OnItemClick(View view, MessageEntity data);
  }

  // 需要外部访问，所以需要设置set方法，方便调用
  private OnItemClickListener onItemClickListener;

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }
}
