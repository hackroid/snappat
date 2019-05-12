/*
 * Created by Snooow on 2019/05/10
 * Edited by RT on 2019/05/13
 */

package com.seclass.snappat.modules.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseFragment;
import com.seclass.snappat.bean.CommonResponse;
import com.seclass.snappat.utils.Utils;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;


public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {
    protected List<Map<String, Object>>  strArr;
    @BindView(R.id.info)
    Button info;
    @BindView(R.id.username)
    TextView userText;
    @BindView(R.id.phone)
    TextView phone;

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

    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.info})
    public void onViewClicked(View v) {
        switch(v.getId()){
            case R.id.info:
                presenter.getUserInfo();
                break;
        }
    }
    @Override
    public void getUserInfoSucc(CommonResponse<JSONObject> msg){
        Log.d("Debug", "getUserInfo: "+msg);
        try{
            //成功
            Log.d("Errno","Good when getUserInfo"+msg.data);
            userText.setText(msg.data.getString("username"));
        }catch(Exception e){
            Log.d("Exception",""+e);
        }
    }
    @Override
    public void getUserInfoFail(CommonResponse<JSONObject> msg){
        if(msg.errno!=0){
            Log.d("Errno","Errno when getUserInfo"+msg.errmsg);
            if(msg.errno==1003){
                //重新注册一下
                presenter.regestry(Utils.getSpUtils().getString("phone"));
//                presenter.getUserInfo();
            }
        }
        hideProgress();
        toast(msg.toString());

    }
}
