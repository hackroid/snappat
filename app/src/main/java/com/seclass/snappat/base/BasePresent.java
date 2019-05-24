package com.seclass.snappat.base;


/**
 * Abstract Class {@code BasePresent}.
 *
 * <p>Implement the attach and detach of activity and view</p>
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */

public abstract class BasePresent<T> {


  public T view;

  /**
   * attach view
   * <p> attach new activity</p>
   * @param view {@code view}
   * @since 3.0
   */
  public void attach(T view) {
    this.view = view;
  }

  /**
   * detach view
   * <p> detach new activity</p>
   * @since 3.0
   */
  public void detach() {
    this.view = null;
  }
}
