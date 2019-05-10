/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.login;

import com.seclass.snappat.base.BaseView;
import com.seclass.snappat.bean.CodeAuthBean;

public interface LoginView extends BaseView {
    void getCodeDataHttp(String message);

    void getCodeAuthDataHttp(CodeAuthBean.DataBean codeAuthBean);

    void getDataHttpFail(String msg);
}
