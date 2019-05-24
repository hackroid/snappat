package com.seclass.snappat.modules.publish;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.model.Response;
import com.seclass.snappat.base.BasePresent;
import com.seclass.snappat.base.BaseUrl;
import com.seclass.snappat.bean.CommonResponse;
import com.seclass.snappat.net.HttpUtils;
import com.seclass.snappat.net.callbck.JsonCallback;
import com.seclass.snappat.utils.Utils;

import org.greenrobot.greendao.annotation.NotNull;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Class {@code PublishPresenter} presenter of publish activity.
 *
 * <p>Aextends class:
 *
 * <p>{@link BasePresent<PublishView>}
 *
 * @author <a href="11611310@mail.sustech.edu.cn">Deyuan Chen</a>
 * @since 2.0
 */
public class PublishPresenter extends BasePresent<PublishView> {

  private Context mContext;

  /**
   * publish presenter
   *
   * <p>initialize presenter of publish activity
   *
   * @param context {@code Context}
   * @since 3.0
   */
  public PublishPresenter(@NotNull Context context) {
    this.mContext = context;
  }

  /**
   * add Mystery
   *
   * <p>add a new mystery ,post to server and get result
   *
   * @param hint {@code String}
   * @param coins {@code String}
   * @param treasure {@code String}
   * @param results {@code String}
   * @since 3.0
   */
  public void addMystery(String hint, String coins, String treasure, String[] results) {

    String phone_number = Utils.getSpUtils().getString("phone_number");
    String username = Utils.getSpUtils().getString("user_name");
    StringBuffer keyBuffer = new StringBuffer();
    if (results != null) {
      for (String s : results) {
        if (s != null && !s.equals("")) {
          keyBuffer.append(s);
        }
        keyBuffer.append(" ");
      }
    }
    String key = keyBuffer.toString();
    Log.i("test key", key);
    HashMap<String, String> hashMap = new HashMap<String, String>();

    HashMap<String, String> postdata = new HashMap<String, String>();
    postdata.put("hint", hint);
    postdata.put("coins", coins);
    postdata.put("treasure", treasure);
    postdata.put("key", key);
    postdata.put("src", "");
    postdata.put("edate", "");

    hashMap.put("phone", phone_number);
    hashMap.put("username", username);

    JSONObject mystery = new JSONObject(postdata);
    hashMap.put("mystery", mystery.toString());

    HttpUtils.postRequest(
        BaseUrl.HTTP_Post_addMystery,
        mContext,
        hashMap,
        new JsonCallback<CommonResponse<CommonResponse.Test>>() {
          @Override
          public void onSuccess(Response<CommonResponse<CommonResponse.Test>> response) {
            Log.d("Debug", "postMystery Response: " + response);
            if (response.body().errno == 0) {
              view.postMysterySucc(response.body().data.getData());
            } else {
              view.postMysteryFail(response.body());
            }
          }
        });
  }
}
