package com.seclass.snappat.modules.publish;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.model.Response;
import com.seclass.snappat.base.BasePresent;
import com.seclass.snappat.base.BaseUrl;
import com.seclass.snappat.bean.CommonResponse;
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


    public void addMystery() {
        String hint=Utils.getSpUtils().getString("hint_text");
        String coins = Utils.getSpUtils().getString("award_value");

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("hint", hint);
        hashMap.put("coins", coins);


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
