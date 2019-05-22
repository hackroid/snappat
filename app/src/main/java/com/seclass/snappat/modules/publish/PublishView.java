package com.seclass.snappat.modules.publish;

import com.seclass.snappat.base.BaseView;
import com.seclass.snappat.bean.CodeAuthBean;
import com.seclass.snappat.bean.CommonResponse;

import org.json.JSONObject;

public interface PublishView extends BaseView {
//    void addMystery(CodeAuthBean.DataBean codeAuthBean);
    void postMysterySucc(JSONObject msg);
    void postMysteryFail(CommonResponse<CommonResponse.Test> msg);

}
