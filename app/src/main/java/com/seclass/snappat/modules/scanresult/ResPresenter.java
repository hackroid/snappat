/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.scanresult;

import android.content.Context;

import com.seclass.snappat.base.BasePresent;

/**
 * class {@code ResPresenter}.
 *
 * <p>result presenter.
 *
 * <p>extend class: {@link BasePresent<ResView>}
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */
public class ResPresenter extends BasePresent<ResView> {
  private Context mContext;

  /**
   * ResPresenter. constructor
   *
   * @param context {@link Context}
   * @since 2.0
   */
  public ResPresenter(Context context) {
    this.mContext = context;
  }
}
