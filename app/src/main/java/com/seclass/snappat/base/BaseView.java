/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.base;

public interface BaseView {
    /**
     * 显示loading框
     */
    void showProgress();

    /**
     * 隐藏loading框
     */
    void hideProgress();

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

    void hideErrorLayout();
}
