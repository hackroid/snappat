/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.welcome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.seclass.snappat.R;
import com.seclass.snappat.app.ActivityUtils;
import com.seclass.snappat.base.BaseActivity;
import com.seclass.snappat.base.BaseUrl;
import com.seclass.snappat.modules.MainActivity;
import com.seclass.snappat.modules.login.LoginActivity;
import com.seclass.snappat.modules.normal.NormalPresenter;
import com.seclass.snappat.modules.normal.NormalView;
import com.seclass.snappat.utils.Utils;
import com.zhy.autolayout.config.AutoLayoutConifg;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * class {@code WelcomeActivity}.
 *
 * <p>WelcomeActivity class.</p>
 * <p>All Implemented Interfaces:</p>
 * <p>{@link NormalView<NormalView, NormalPresenter>}</p>
 * <p>extends class:</p>
 * <p>{@link BaseActivity}</p>
 *
 * @author <a href="mobile_app@sustechapp.com">Sen Wang</a>
 * @since 2.0
 */

public class WelcomeActivity extends BaseActivity<NormalView, NormalPresenter> implements NormalView {
    private static final int TIME = 500;
    private static final int GO_HOME = 200;
    private static final int GO_LOGIN = 300;
    public SPCookieStore mSPCookieStore;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_LOGIN:
                    goLogin();
                    break;
            }
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        if (Utils.getSpUtils().getString("user_name") == null) {
            mHandler.sendEmptyMessageDelayed(GO_LOGIN, TIME);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
        }
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Utils.init(getApplicationContext());
        AutoLayoutConifg.getInstance().useDeviceSize();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkGo.getInstance().setOkHttpClient(builder.build());
        try {
            initOkGo();
        } catch (IOException e) {
            Log.e("init Go", "onCreate: ",e );
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    private void goHome() {
        ActivityUtils.next(WelcomeActivity.this, MainActivity.class, true);
    }


    private void goLogin() {
        ActivityUtils.next(WelcomeActivity.this, LoginActivity.class, true);
    }

    /**
     * init base.
     * <p>init Presenter</p>
     * @throws IOException IOException
     * @since 2.0
     */
    public void initOkGo() throws IOException {
        mSPCookieStore = new SPCookieStore(this);
        HttpHeaders headers  = new HttpHeaders();
        if (new SPCookieStore(this).getAllCookie().size() != 0) {
            headers.put("Set-Cookie", String.valueOf(mSPCookieStore.getCookie(HttpUrl.parse(BaseUrl.HTTP_Get_code_auth))));
        }
        headers.put("version", "3.0");
        headers.put("uid", "6f1a8e0eb24afb7ddc829f96f9f74e9d");


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkHttp");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
        //超时时间设置
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);   //全局的连接超时时间
        builder.cookieJar(new CookieJarImpl(mSPCookieStore));            //使用sp保持cookie，如果cookie不过期，则一直有效


        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(getAssets().open("server.cer"));
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
//        //配置https的域名匹配规则，使用不当会导致https握手失败
        builder.hostnameVerifier(HttpsUtils.UnSafeHostnameVerifier);

        // 其他统一的配置
        OkGo.getInstance().init(this.getApplication())                           //必须调用初始化
                .setOkHttpClient(builder.build())               //必须设置OkHttpClient
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)          //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers);              //全局公共头

    }

}
