/*
 * Created by Snooow on 2019/5/10
 */
package com.seclass.snappat.net.callbck;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.Type;

public class Convert {

  public static <T> T fromJson(JsonReader reader, Type typeOfT)
      throws JsonIOException, JsonSyntaxException {
    return create().fromJson(reader, typeOfT);
  }

  private static Gson create() {
    return GsonHolder.gson;
  }

  public static String toJson(Object src) {
    return create().toJson(src);
  }

  public static String toJson(Object src, Type typeOfSrc) {
    return create().toJson(src, typeOfSrc);
  }

  private static class GsonHolder {
    private static Gson gson = new Gson();
  }
}
