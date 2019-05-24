package com.seclass.snappat.bean;

import android.util.Log;
import java.io.Serializable;
import org.json.JSONObject;

/**
 * Class {@code CommonResponse} Internet response data class.
 *
 * <p>Internet response data class with necessary method</p>
 * <p>All Implemented Interfaces:</p>
 * <p>{@link Serializable}</p>
 * @author <a href="11612717@mail.sustech.edu.cn">Deyuan Chen</a>
 * @since 2.0
 */
public class CommonResponse<T> implements Serializable {

  public int errno;

  public String errmsg;

  public T data;

  /**
   * record Login Presenter .
   *
   * @return errno
   * @since 1.0
   */
  public int getCode() {
    return errno;
  }

  /**
   * record Login Presenter .
   * @param code {@code int}
   * @since 1.0
   */
  public void setErrno(int code) {
    this.errno = code;
  }

  /**
   * set errmsg .
   * @param errmsg {@code string}
   * @since 1.0
   */
  public void setErrmsg(String errmsg){this.errmsg = errmsg;}

  public String getErrmsg(){return this.errmsg;}

  /**
   * get data.
   * @return data
   * @since 1.0
   */
  public T getData(){return data;};

  /**
   * set data.
   * @param data {@code T}
   * @since 1.0
   */
  public void setData(T data) {
    this.data = data;
  }

  public String string(){return this.data.toString();}

  public static class Test{
    public String dataString;

    /**
     * set data.
     * @return jsonObject
     * @since 1.0
     */
    public JSONObject getData(){
      JSONObject jsonObject=null;
      try {
        jsonObject = new JSONObject(this.dataString);
        return jsonObject;
      }catch(Exception e){
        Log.d("Exception",""+e);
      }
    return jsonObject;
    }
  }
}