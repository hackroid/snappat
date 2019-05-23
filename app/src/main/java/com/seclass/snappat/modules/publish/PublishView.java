package com.seclass.snappat.modules.publish;

import com.seclass.snappat.base.BaseView;
import com.seclass.snappat.bean.CommonResponse;

import org.json.JSONObject;

/**
 * Class {@code PublishActivity} publish mystery activity of this app.
 *
 * <p>After click picture take btn, this activity will appear and help user to publish a new mystery</p>
 * <p>extends {@code BaseActivity} with {@code PublishView} and {@code PublishPresenter} </p>
 * <p>implement {@code PublishView}</p>
 *
 * @author <a href="11611310@mail.sustech.edu.cn">Deyuan Chen</a>
 * @since 2.0
 */
public interface PublishView extends BaseView {
//    void addMystery(CodeAuthBean.DataBean codeAuthBean);
    /**
     * publish mystery successfully
     * <p> show a toast with "publish" when publish mystery successfully</p>
     * @param msg {@code JSONObject}
     * @since 3.0
     */
    void postMysterySucc(JSONObject msg);

    /**
     * publish mystery failed
     * <p> show a toast with "Errno" when publish mystery fail</p>
     * @param msg {@code CommonResponse<Test>}
     * @since 3.0
     */
    void postMysteryFail(CommonResponse<CommonResponse.Test> msg);

}
