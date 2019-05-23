/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.base;

/**
 * Interface {@code BaseView}.
 *
 * <p>Basic interface for all activity view.
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */

public interface BaseView {
    /**
     * 显示loading框
     */
    void showProgress();

    /**
     * 隐藏loading框
     */
    void hideProgress();

    /**
     * show toast
     */
    void toast(CharSequence s);

    /**
     * 显示空数据布局
     */
    void showNullLayout();

    /**
     * 隐藏空数据布局
     */
    void hideNullLayout();

    /**
     * 显示异常布局
     */
    void showErrorLayout();

    /**
     * 隐藏异常布局
     */
    void hideErrorLayout();
}
