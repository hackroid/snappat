/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.home;

import android.content.Context;

import android.util.Log;
import com.lzy.okgo.model.Response;
import com.seclass.snappat.base.BasePresent;
import com.seclass.snappat.base.BaseUrl;
import com.seclass.snappat.bean.CommonResponse;
import com.seclass.snappat.bean.CommonResponse.Test;
import com.seclass.snappat.net.HttpUtils;
import com.seclass.snappat.net.callbck.JsonCallback;
import com.seclass.snappat.utils.Utils;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class HomePresenter extends BasePresent<HomeView> {
    private Context mContext;

    public HomePresenter(Context context) {
        this.mContext = context;
    }

    public void getPuzzleInfo() {
        String phone_number= Utils.getSpUtils().getString("phone_number");
        String username = Utils.getSpUtils().getString("user_name");
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("phone", phone_number);
        hashMap.put("username", username);
        Log.d("DataTest",phone_number);
        Log.d("DataTest",username);
        HttpUtils.postRequest(BaseUrl.HTTP_Get_mystery, mContext, hashMap, new JsonCallback<CommonResponse<Test>>() {
            @Override
            public void onSuccess(Response<CommonResponse<Test>> response) {
                JSONArray puzzleResponseList=null;
                Log.d("Debug", "getPuzzleInfo Response: "+response);
                if (response.body().errno == 0) {
                    try{
                        puzzleResponseList = new JSONArray(response.body().getData().dataString);
                        Log.d("Debug", "getPuzzleInfo Response Class:" + puzzleResponseList.toString());
                    } catch (Exception e) {
                        Log.d("Exception", "getPuzzleInfo Response: null object reference" + e);
                    }
                    view.getPuzzleSucc(puzzleResponseList);
                } else {
                    view.getPuzzleFail(response.body());
                }
            }
        });
    }

}
