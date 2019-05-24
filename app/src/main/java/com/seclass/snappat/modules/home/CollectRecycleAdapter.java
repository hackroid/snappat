package com.seclass.snappat.modules.home;

import android.content.Context;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.seclass.snappat.R;
import java.util.ArrayList;

public class CollectRecycleAdapter
    extends RecyclerView.Adapter<CollectRecycleAdapter.myViewHodler> {
  private Context context;
  private ArrayList<GoodsEntity> goodsEntityList;

  // 创建构造函数
  public CollectRecycleAdapter(Context context, ArrayList<GoodsEntity> goodsEntityList) {
    // 将传递过来的数据，赋值给本地变量
    this.context = context; // 上下文
    this.goodsEntityList = goodsEntityList; // 实体类数据ArrayList
  }

  public void notifyDataChanged(Context context, ArrayList<GoodsEntity> goodsEntityList) {
    this.context = context; // 上下文
    this.goodsEntityList = goodsEntityList;
    this.notifyDataSetChanged();
  }

  /**
   * 创建viewholder，相当于listview中getview中的创建view和viewhodler
   *
   * @param parent
   * @param viewType
   * @return
   */
  @Override
  public myViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
    // 创建自定义布局
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card, parent, false);
    //    View itemView = View.inflate(context, R.layout.fragment_card, null);
    return new myViewHodler(itemView);
  }

  /**
   * 绑定数据，数据与view绑定
   *
   * @param holder
   * @param position
   */
  @Override
  public void onBindViewHolder(myViewHodler holder, int position) {
    // 根据点击位置绑定数据
    GoodsEntity data = goodsEntityList.get(position);
    //        holder.mItemGoodsImg;
    holder.mItemGoodsUsername.setText(data.goodsHint); // 获取实体类中的name字段并设置
    holder.mItemGoodsPrice.setText(data.goodsPrice); // 获取实体类中的price字段并设置
    holder.mItemGoodsHint.setText(data.goodsHint);
  }

  /**
   * 得到总条数
   *
   * @return
   */
  @Override
  public int getItemCount() {
    return goodsEntityList.size();
  }

  // 自定义viewhodler
  class myViewHodler extends RecyclerView.ViewHolder {
    private TextView mItemGoodsUsername;
    private TextView mItemGoodsPrice;
    private TextView mItemGoodsHint;
    private Button solveButton;
    private Button likeButton;

    public myViewHodler(View itemView) {
      super(itemView);
      mItemGoodsUsername = (TextView) itemView.findViewById(R.id.card_username);
      mItemGoodsPrice = (TextView) itemView.findViewById(R.id.card_price);
      mItemGoodsHint = (TextView) itemView.findViewById(R.id.card_hint);
      solveButton = (Button) itemView.findViewById(R.id.solve_button);
      likeButton = (Button) itemView.findViewById(R.id.like_button);
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
                onItemClickListener.OnItemClick(v, goodsEntityList.get(getLayoutPosition()));
              }
            }
          });
      likeButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          Toast.makeText(context,"点击了zan",Toast.LENGTH_SHORT).show();
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
    public void OnItemClick(View view, GoodsEntity data);
  }

  // 需要外部访问，所以需要设置set方法，方便调用
  private OnItemClickListener onItemClickListener;

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }
}
