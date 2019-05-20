/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.publish;

import android.content.Context;

import com.seclass.snappat.base.BasePresent;

public class PubPresenter extends BasePresent<PubView> {
    private Context mContext;

    public PubPresenter(Context context) {
        this.mContext = context;
    }
}
