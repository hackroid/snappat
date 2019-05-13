package com.seclass.snappat.modules.publish;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.seclass.snappat.base.BasePresent;
import com.seclass.snappat.base.BaseUrl;
import com.seclass.snappat.bean.ResponseBean;
import com.seclass.snappat.net.HttpUtils;
import com.seclass.snappat.net.callbck.JsonCallback;
import com.seclass.snappat.utils.Utils;

import org.json.JSONObject;

import java.util.HashMap;

public class PublishPresenter extends BasePresent<PublishView> {

    private Context mContext;

    public PublishPresenter(Context context) {
        this.mContext = context;
    }

    public void getMesteryData(String hint, String award, String message){
        String phone_number= Utils.getSpUtils().getString("phone_number");
        String username = Utils.getSpUtils().getString("user_name");
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("phone", phone_number);
        hashMap.put("username", username);
//        HttpUtils.postRequest(BaseUrl.HTTP_Post_addMystery, mContext, hashMap, new JsonCallback<ResponseBean<JSONObject>>() {
//            @Override
//            public void onSuccess(Response<ResponseBean<JSONObject>> response) {
//                if (response.body().code == 0) {
//
//                } else {
//
//                }
//            }
//        });


    }
}
