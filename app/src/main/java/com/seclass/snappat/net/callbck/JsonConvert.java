/*
 * Created by Snooow on 2019/5/10
 */
package com.seclass.snappat.net.callbck;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.convert.Converter;
import com.seclass.snappat.bean.BaseResponseBean;
import com.seclass.snappat.bean.ResponseBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/** Created by pocketEos on 2018/4/2. */
public class JsonConvert<T> implements Converter<T> {

  private Type type;
  private Class<T> clazz;

  public JsonConvert() {}

  public JsonConvert(Type type) {
    this.type = type;
  }

  public JsonConvert(Class<T> clazz) {
    this.clazz = clazz;
  }

  /**
   * 该方法是子线程处理，不能做ui相关的工作 主要作用是解析网络返回的 response 对象，生成onSuccess回调中需要的数据对象
   * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
   */
  @Override
  public T convertResponse(Response response) throws Throwable {

    if (type == null) {
      if (clazz == null) {
        // 如果没有通过构造函数传进来，就自动解析父类泛型的真实类型（有局限性，继承后就无法解析到）
        Type genType = getClass().getGenericSuperclass();
        type = ((ParameterizedType) genType).getActualTypeArguments()[0];
      } else {
        return parseClass(response, clazz);
      }
    }

    if (type instanceof ParameterizedType) {
      return parseParameterizedType(response, (ParameterizedType) type);
    } else if (type instanceof Class) {
      return parseClass(response, (Class<?>) type);
    } else {
      return parseType(response, type);
    }
  }

  private T parseClass(Response response, Class<?> rawType) throws Exception {
    if (rawType == null) return null;
    ResponseBody body = response.body();
    if (body == null) return null;
    JsonReader jsonReader = new JsonReader(body.charStream());

    if (rawType == String.class) {
      //noinspection unchecked
      return (T) body.string();
    } else if (rawType == JSONObject.class) {
      //noinspection unchecked
      return (T) new JSONObject(body.string());
    } else if (rawType == JSONArray.class) {
      //noinspection unchecked
      return (T) new JSONArray(body.string());
    } else {
      T t = Convert.fromJson(jsonReader, rawType);
      response.close();
      return t;
    }
  }

  private T parseType(Response response, Type type) throws Exception {
    if (type == null) return null;
    ResponseBody body = response.body();
    if (body == null) return null;
    JsonReader jsonReader = new JsonReader(body.charStream());

    // 泛型格式如下： new JsonCallback<任意JavaBean>(this)
    T t = Convert.fromJson(jsonReader, type);
    response.close();
    return t;
  }

  private T parseParameterizedType(Response response, ParameterizedType type) throws Exception {
    if (type == null) return null;
    ResponseBody body = response.body();
    if (body == null) return null;
    JsonReader jsonReader = new JsonReader(body.charStream());

    Type rawType = type.getRawType(); // 泛型的实际类型
    Type typeArgument = type.getActualTypeArguments()[0]; // 泛型的参数
    if (rawType != ResponseBean.class) {
      // 泛型格式如下： new JsonCallback<外层BaseBean<内层JavaBean>>(this)
      T t = Convert.fromJson(jsonReader, type);
      response.close();
      return t;
    } else {
      if (typeArgument == Void.class) {
        // 泛型格式如下： new JsonCallback<ResponseBean<Void>>(this)
        BaseResponseBean baseResponseBean = Convert.fromJson(jsonReader, BaseResponseBean.class);
        response.close();
        //noinspection unchecked
        return (T) baseResponseBean.toResponseBean();
      } else {
        // 泛型格式如下： new JsonCallback<ResponseBean<内层JavaBean>>(this)
        ResponseBean responseBean = Convert.fromJson(jsonReader, type);
        response.close();
        return (T) responseBean;
      }
    }
  }
}
