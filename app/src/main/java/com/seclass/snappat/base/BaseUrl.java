package com.seclass.snappat.base;

public class BaseUrl {
    // 验证服务器
    public final static String HTTP_ADDRESS = "https://api.pocketeos.top/api_oc_personal/v1.0.0/";
    public final static String Server_Url="http://cn1.hackroid.com:8888/";

    // 获取验证码
    public final static String HTTP_Get_code = HTTP_ADDRESS + "message/send";
    // 绑定手机号
    public final static String HTTP_bind_phone_number = HTTP_ADDRESS + "user/bind_phone_number";
    // 验证获取验证码是否正确
    public final static String HTTP_Get_code_auth = HTTP_ADDRESS + "message/auth";

    //以下API的详细使用方法见后端文档
    public final static String HTTP_Post_Registry = Server_Url + "registry/registry";
    //通过username和phone登录并返回用户信息(登录)
    public final static String HTTP_Get_userinfo = Server_Url + "user/select";
    //获取用户发布过的谜题信息,需要登录状态
    public final static String HTTP_Get_mystery = Server_Url + "user/selectMystery";
    //获取用户解密记录信息,需要登录状态
    public final static String HTTP_Get_history= Server_Url + "user/selectHistory";
    //获取用户全部消息,需要登录状态
    public final static String HTTP_Get_message= Server_Url + "user/selectMessage";
    //通过用户id获取指定用户信息,需要登录状态
    public final static String HTTP_Get_select= Server_Url + "user/selectUser";

    //发布一条谜题,需要登录状态
    public final static String HTTP_Post_addMystery= Server_Url + "user/addMystery";
    //更新一条谜题内容、评论、访问量等信息,需要登录状态
    public final static String HTTP_Post_updateMystery= Server_Url + "user/updateMystery";
    //添加一条对mystery的破解信息,需要登录状态
    public final static String HTTP_Post_crackMystery= Server_Url + "user/crackMystery";
}
