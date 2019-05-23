/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.notify;

import android.os.Bundle;


import com.seclass.snappat.R;
import com.seclass.snappat.base.BaseFragment;
import com.seclass.snappat.bean.ResponseBean;

/**
 * class {@code NotifyFragment}.
 *
 * <p>base notify fragment.</p>
 * <p> all implements</p>
 * <p>{@link NotifyView}</p>
 * <p>extends {@link BaseFragment<NotifyView, NotifyPresenter>}</p>
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */
public class NotifyFragment extends BaseFragment<NotifyView, NotifyPresenter> implements NotifyView {

    @Override
    public NotifyPresenter initPresenter() {
        return new NotifyPresenter(getActivity());
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
        return R.layout.fragment_notify;
    }

    @Override
    public ResponseBean getNotify() {
        return null;
    }
}
