/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.login;

import android.content.Context;
import android.util.Log;
import com.lzy.okgo.model.Response;
import com.seclass.snappat.base.BasePresent;
import com.seclass.snappat.base.BaseUrl;
import com.seclass.snappat.bean.CodeAuthBean;
import com.seclass.snappat.bean.CommonResponse;
import com.seclass.snappat.bean.ResponseBean;
import com.seclass.snappat.net.HttpUtils;
import com.seclass.snappat.net.callbck.JsonCallback;
import java.util.HashMap;
import org.json.JSONObject;


/**
 * Class {@code LoginPresenter} .
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */


public class LoginPresenter extends BasePresent<LoginView> {

  private Context mContext;

  public LoginPresenter(Context context) {
    this.mContext = context;
  }


  /**
   * Get code .
   *
   * <p>Get code with phone number from server</p>
   *
   * @return {@code UserInfo}
   * @since 1.0
   */

  public void getCodeData(String mobilephone) {
    HashMap<String, String> hashMap = new HashMap<String, String>();
    hashMap.put("phoneNum", mobilephone);
    HttpUtils.postRequest(BaseUrl.HTTP_Get_code, mContext, hashMap,
        new JsonCallback<ResponseBean<String>>() {
          @Override
          public void onSuccess(Response<ResponseBean<String>> response) {
            if (response.body().code == 0) {
              view.getCodeDataHttp(response.body().message);
            } else {
              view.getDataHttpFail(response.body().message);
            }
          }
        });
    }

    public void regestry(String phone){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("phone", phone);
        hashMap.put("username", "新用户");
        HttpUtils.postRequest(BaseUrl.HTTP_Post_Registry,mContext,hashMap,new JsonCallback<CommonResponse<JSONObject>>() {
            @Override
            public void onSuccess(Response<CommonResponse<JSONObject>> response) {
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

    public void getcodeAuthData(String mobilephone, String code) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("phoneNum", mobilephone);
        hashMap.put("code", code);
        HttpUtils.postRequest(BaseUrl.HTTP_Get_code_auth, mContext, hashMap, new JsonCallback<ResponseBean<CodeAuthBean.DataBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<CodeAuthBean.DataBean>> response) {
                if (response.body().code == 0) {
                    view.getCodeAuthDataHttp(response.body().data);

                    Log.d("Debug", "getcodeAuthData: "+response.body().data);

                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
          }
        });
  }
}
