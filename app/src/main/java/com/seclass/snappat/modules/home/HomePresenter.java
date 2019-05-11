/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.home;

import android.content.Context;

import com.seclass.snappat.base.BasePresent;

public class HomePresenter extends BasePresent<HomeView> {
    private Context mContext;

    public HomePresenter(Context context) {
        this.mContext = context;
    }

}
