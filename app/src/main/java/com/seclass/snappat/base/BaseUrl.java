package com.seclass.snappat.base;

public class BaseUrl {
    // 验证服务器
    public final static String HTTP_ADDRESS = "https://api.pocketeos.top/api_oc_personal/v1.0.0/";
    // 获取验证码
    public final static String HTTP_Get_code = HTTP_ADDRESS + "message/send";
    // 绑定手机号
    public final static String HTTP_bind_phone_number = HTTP_ADDRESS + "user/bind_phone_number";
    // 验证获取验证码是否正确
    public final static String HTTP_Get_code_auth = HTTP_ADDRESS + "message/auth";
}
