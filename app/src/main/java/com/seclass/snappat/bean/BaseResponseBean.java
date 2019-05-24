/*
 * Created by Snooow on 2019/5/10
 */
package com.seclass.snappat.bean;

import com.seclass.snappat.base.BaseView;
import com.zhy.autolayout.AutoLayoutActivity;
import java.io.Serializable;
import javax.annotation.Nullable;

/**
 * abstract class {@code BaseActivity}.
 *
 * <p>base response data form.
 *
 * <p>All Implemented Interfaces:
 *
 * <p>{@link Serializable}
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */
public class BaseResponseBean implements Serializable {

  private static final long serialVersionUID = -1477609349345966116L;

  public int code;
  public String message;

  /**
   * change to response Bean.
   *
   * @return responseBean {@link ResponseBean}
   * @since 2.0
   */
  public ResponseBean toResponseBean() {
    ResponseBean responseBean = new ResponseBean();
    responseBean.code = code;
    responseBean.message = message;
    return responseBean;
  }

  /**
   * get code.
   *
   * @return code
   * @since 2.0
   */
  public int getCode() {
    return code;
  }

  /**
   * set code.
   *
   * @param code {@code int}
   * @since 2.0
   */
  public void setCode(@Nullable int code) {
    this.code = code;
  }

  /**
   * get message.
   *
   * @return message {@code String}
   * @since 2.0
   */
  public String getMessage() {
    return message == null ? "" : message;
  }

  /**
   * set message.
   *
   * @param message {@code String}
   * @since 2.0
   */
  public void setMessage(@Nullable String message) {
    this.message = message;
  }
}
