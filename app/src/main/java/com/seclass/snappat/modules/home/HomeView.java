/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.home;

import com.seclass.snappat.base.BaseView;
import com.seclass.snappat.bean.CommonResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public interface HomeView extends BaseView {
  void getPuzzleSucc(JSONArray msg);

  void getPuzzleFail(CommonResponse<CommonResponse.Test> msg);

}
