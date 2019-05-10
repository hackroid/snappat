/*
 * Created by Snooow on 2019/5/10
 */
package com.seclass.snappat.net.callbck;

import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.callback.AbsCallback;
import com.seclass.snappat.R;
import com.seclass.snappat.utils.ShowDialog;
import com.seclass.snappat.utils.ToastUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.Response;

public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;


    public JsonCallback() {

    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }


    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     */
    @Override
    public T convertResponse(Response response) throws Throwable {
        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }

        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);
    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        ShowDialog.dissmiss();
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        ShowDialog.dissmiss();
        int code = response.code();
        if (code == 404) {
            ToastUtils.showLongToast(R.string.url_error);
        }
        if (response.getException() instanceof SocketTimeoutException) {
            ToastUtils.showLongToast(R.string.socket_time_out);
        } else if (response.getException() instanceof SocketException) {
            ToastUtils.showLongToast(R.string.socket_exception);
        } else if (response.getException() instanceof JsonSyntaxException) {
            ToastUtils.showLongToast(R.string.error_parse);
        }
    }
}
