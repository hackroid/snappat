package com.seclass.snappat.bean;

import java.io.Serializable;


public class CommonResponse<JSONObject> implements Serializable {

  public int errno;
  public String errmsg;
  public JSONObject data = null;
  public int getCode() {
    return errno;
  }
  public void setErrno(int code) {
    this.errno = code;
  }
  public void setErrmsg(String errmsg){this.errmsg = errmsg;}
  public String getErrmsg(){return this.errmsg;}
  public JSONObject getData(){return data;};
  public void setData(JSONObject data) {
    this.data = data;
  }
}