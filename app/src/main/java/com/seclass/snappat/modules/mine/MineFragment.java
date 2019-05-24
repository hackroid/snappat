/*
 * Created by Snooow on 2019/05/10
 * Edited by RT on 2019/05/13
 */

package com.seclass.snappat.modules.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import butterknife.BindView;
import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseFragment;
import com.seclass.snappat.bean.CommonResponse;
import com.seclass.snappat.bean.CommonResponse.Test;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * class {@code MineFragment}.
 *
 * <p>Self Information Page fragment.</p>
 * <p>All Implemented Interfaces:</p>
 * <p>{@link MineView}</p>
 * <p>extends class:</p>
 * <p>{@link BaseFragment<MineView, MinePresenter>}</p>
 *
 * @author <a href="11612717@mail.sustech.edu.com">Tao Ren</a>
 * @since 3.0
 */
public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {
    protected List<Map<String, Object>>  strArr;
    @BindView(R.id.username)
    TextView userText;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.follower)
    TextView follower;
    @BindView(R.id.coin)
    TextView coin;
    @BindView(R.id.description)
    TextView description;

    @Override
    public MinePresenter initPresenter() {
        return new MinePresenter(getActivity());
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void initEvent() {
        presenter.getUserInfo();
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_mine;
    }


    @Override
    public void getUserInfoSucc(JSONObject msg){
        try{
            //成功
            String username = msg.getString("username");
            String phone_number = msg.getString("phone");
            String des = msg.getString("description");
            if(des.length()==0){
                des="这个人很懒,什么都没写";
            }
            String c = msg.getString("coins");
            String fl = msg.getString("follower");
            if(fl.equals("[]")){
                fl="";
            }
            userText.setText("用户名: "+username);
            Log.d("debug-username", username);
            phone.setText("电话: "+phone_number);
            description.setText("简介:"+des);
            coin.setText("金币:"+c);
            follower.setText("关注你的人:"+fl.length());
        }catch(Exception e){
            Log.d("Exception",""+e);
        }
    }

    @Override
    public void getHistoryInfoSucc(JSONObject msg){
        try{
            //成功

        }catch(Exception e){
            Log.d("Exception",""+e);
        }
    }

    @Override
    public void getHistoryInfoFail(CommonResponse<Test> msg){
        if(msg.errno!=0){
            Log.d("Errno","Errno when getUserInfo"+msg.errmsg);
        }
        hideProgress();
        toast(msg.toString());

    }

    @Override
    public void getUserInfoFail(CommonResponse<Test> msg){
        if(msg.errno!=0){
            Log.d("Errno","Errno when getUserInfo"+msg.errmsg);
            if(msg.errno==1003){
                //重新注册一下
//                presenter.regestry(Utils.getSpUtils().getString("phone"));
//                presenter.getUserInfo();
            }
        }
        hideProgress();
        toast(msg.toString());
    }
}