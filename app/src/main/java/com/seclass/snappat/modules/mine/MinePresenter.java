/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.mine;


import android.content.Context;
import android.util.Log;
import com.lzy.okgo.model.Response;
import com.seclass.snappat.base.BasePresent;
import com.seclass.snappat.base.BaseUrl;
import com.seclass.snappat.bean.CommonResponse;
import com.seclass.snappat.net.HttpUtils;
import com.seclass.snappat.net.callbck.JsonCallback;
import com.seclass.snappat.utils.Utils;
import java.util.HashMap;


public class MinePresenter  extends BasePresent<MineView> {
    private Context mContext;

    public MinePresenter(Context context) {
        this.mContext = context;
    }

    public void getUserInfo() {
        String phone_number=Utils.getSpUtils().getString("phone_number");
        String username = Utils.getSpUtils().getString("user_name");
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("phone", phone_number);
        hashMap.put("username", username);
        Log.d("DataTest",phone_number);
        Log.d("DataTest",username);
        HttpUtils.postRequest(BaseUrl.HTTP_Get_userinfo, mContext, hashMap, new JsonCallback<CommonResponse<CommonResponse.Test>>() {
            @Override
            public void onSuccess(Response<CommonResponse<CommonResponse.Test>> response) {
                Log.d("Debug", "getUserInfo Response: "+response);
                if (response.body().errno == 0) {
                    view.getUserInfoSucc(response.body().data.getData());
                } else {
                    view.getUserInfoFail(response.body());
                }
            }
        });
    }
    public void regestry(String phone){
        String phone_number=Utils.getSpUtils().getString("phone_number");
        String username=Utils.getSpUtils().getString("user_name");
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("phone", phone_number);
        hashMap.put("username",username);
        HttpUtils.postRequest(BaseUrl.HTTP_Post_Registry,mContext,hashMap,new JsonCallback<CommonResponse<String>>() {
            @Override
            public void onSuccess(Response<CommonResponse<String>> response) {
                Log.d("Debug", "regestry: "+response);
                Log.d("Debug","datastructure"+response.body());
                if (response.body().errno == 0) {
                    Log.d("Succ","注册成功");
                } else {
                    Log.d("Succ","注册失败"+response.body());
                }
            }
        });
    }
}
