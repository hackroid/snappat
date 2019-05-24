package com.seclass.snappat.bean;

import java.io.Serializable;

/**
 * Class {@code ResponseBean} Internet response data class.
 *
 * <p>Internet response data class with necessary method
 *
 * <p>All Implemented Interfaces:
 *
 * <p>{@link Serializable}
 *
 * @author <a href="11612717@mail.sustech.edu.cn">Deyuan Chen</a>
 * @since 2.0
 */
public class ResponseBean<T> implements Serializable {

  public int code;
  public String message;
  public T data = null;
}
