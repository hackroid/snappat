/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.notify;

import android.os.Bundle;

import com.seclass.snappat.base.BaseFragment;
import com.seclass.snappat.bean.ResponseBean;

public class NotifyFragment extends BaseFragment<NotifyView, NotifyPresenter> implements NotifyView {
    @Override
    public NotifyPresenter initPresenter() {
        return new NotifyPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }

    @Override
    public ResponseBean getNotify() {
        return null;
    }
}
