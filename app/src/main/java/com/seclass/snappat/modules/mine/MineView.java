/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.mine;

import com.seclass.snappat.base.BaseView;
import com.seclass.snappat.bean.CommonResponse;
import org.json.JSONObject;

public interface MineView extends BaseView {

  void getUserInfoSucc(JSONObject msg);

  void getUserInfoFail(CommonResponse<CommonResponse.Test> msg);
}
