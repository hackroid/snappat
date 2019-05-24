/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.bean;

import javax.annotation.Nullable;

/**
 * class {@code CodeAuthBean}.
 *
 * <p>code auth class.
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */
public class CodeAuthBean {

  /** code : 0 message : 验证码验证成功 data : {"uid":"d553e2e94b9b888341fe1d572b6720b5"} */
  private int code;

  private String message;
  private DataBean data;

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
   * get Message.
   *
   * @return message {@code String}
   * @since 2.0
   */
  public String getMessage() {
    return message;
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

  /**
   * data bean.
   *
   * @return data
   * @since 2.0
   */
  public DataBean getData() {
    return data;
  }

  /**
   * set data.
   *
   * @param data {@code DataBean}
   * @since 2.0
   */
  public void setData(@Nullable DataBean data) {
    this.data = data;
  }

  /**
   * static class DataBean.
   *
   * @since 2.0
   */
  public static class DataBean {
    /** uid : d553e2e94b9b888341fe1d572b6720b5 */
    private String uid;

    /**
     * get uid.
     *
     * @return uid {@code String}
     * @since 2.0
     */
    public String getUid() {
      return uid == null ? "" : uid;
    }

    /**
     * get uid.
     *
     * @param uid {@code Stringn}
     * @since 2.0
     */
    public void setUid(@Nullable String uid) {
      this.uid = uid;
    }
  }
}
