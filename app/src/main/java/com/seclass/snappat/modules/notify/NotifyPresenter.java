/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.notify;

import android.content.Context;

import com.seclass.snappat.base.BasePresent;

public class NotifyPresenter extends BasePresent<NotifyView> {
    private Context mContext;

    public NotifyPresenter(Context context) {
        this.mContext = context;
    }
}
