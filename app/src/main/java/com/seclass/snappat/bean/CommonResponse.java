package com.seclass.snappat.bean;

import android.util.Log;
import java.io.Serializable;
import org.json.JSONObject;


public class CommonResponse<T> implements Serializable {

  public int errno;
  public String errmsg;
  public T data;
  public int getCode() {
    return errno;
  }
  public void setErrno(int code) {
    this.errno = code;
  }
  public void setErrmsg(String errmsg){this.errmsg = errmsg;}
  public String getErrmsg(){return this.errmsg;}
  public T getData(){return data;};
  public void setData(T data) {
    this.data = data;
  }
  public String string(){return this.data.toString();}
  public static class Test{
    public String dataString;
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