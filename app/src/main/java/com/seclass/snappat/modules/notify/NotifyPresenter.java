/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.notify;

import android.content.Context;

import com.seclass.snappat.base.BasePresent;

/**
 * class {@code NotifyPresenter}.
 *
 * <p>notify presenter.</p>
 * <p>extends {@link BasePresent<NotifyView>}</p>
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */
public class NotifyPresenter extends BasePresent<NotifyView> {
    private Context mContext;

    /**
     * Notify Presenter.
     * @param context {@link Context}
     * @since 2.0
     */
    public NotifyPresenter(Context context) {
        this.mContext = context;
    }
}
