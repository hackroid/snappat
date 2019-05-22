package com.seclass.snappat.modules.publish;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.view.View;

import com.lzy.okgo.model.Response;
import com.seclass.snappat.R;
import com.seclass.snappat.base.BasePresent;
import com.seclass.snappat.base.BaseUrl;
import com.seclass.snappat.bean.CommonResponse;
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


    public void addMystery(String hint, String coins, String treasure, String[] results) {

        String phone_number=Utils.getSpUtils().getString("phone_number");
        String username=Utils.getSpUtils().getString("user_name");
        StringBuffer keyBuffer = new StringBuffer();
        for (String s: results){
            keyBuffer.append(s);
        }
        String key = keyBuffer.toString();
        System.out.println("key: "+key);

        HashMap<String, String> hashMap = new HashMap<String, String>();

        HashMap<String, String> postdata = new HashMap<String, String>();
        postdata.put("hint",  hint);
        postdata.put("coins", coins);
        postdata.put("treasure", treasure);
        postdata.put("key", key);
        postdata.put("src", "");
        postdata.put("edate", "");

        hashMap.put("phone", phone_number);
        hashMap.put("username",username);

        JSONObject mystery = new JSONObject(postdata);
        hashMap.put("mystery", mystery.toString());

        HttpUtils.postRequest(BaseUrl.HTTP_Post_addMystery, mContext, hashMap, new JsonCallback<CommonResponse<CommonResponse.Test>>() {
            @Override
            public void onSuccess(Response<CommonResponse<CommonResponse.Test>> response) {
                Log.d("Debug", "postMystery Response: "+response);
                if (response.body().errno == 0) {
                    view.postMysterySucc(response.body().data.getData());
                } else {
                    view.postMysteryFail(response.body());
                }
            }
        });
    }

}
