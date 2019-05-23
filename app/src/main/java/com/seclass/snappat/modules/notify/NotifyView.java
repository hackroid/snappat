/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.notify;

import com.seclass.snappat.base.BaseView;
import com.seclass.snappat.bean.ResponseBean;

/**
 * class {@code NotifyView}.
 *
 * <p>notify view.</p>
 * <p>extends {@link BaseView}</p>
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */
public interface NotifyView extends BaseView {
    /**
     * get notify.
     * @return null
     * @since 2.0
     */
    public ResponseBean getNotify();

}
