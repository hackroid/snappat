package com.test.rentao.testjavaprojects;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    //定义一个图片显示控件  
    private ImageView imageView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.main_content1);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551694526845&di=4c9c7b88a6a7106cc3d962cc8fc2ba40&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fa783a4a7e97dfb09567ff069cdedce49192c02dc23b2-HgslCY_fw658";
        //得到可用的图片  
        Bitmap bitmap = getHttpBitmap(url);  
        imageView = (ImageView)this.findViewById(R.id.imageView);
        //显示  
        imageView.setImageBitmap(bitmap);  
    }
     /** 
      * 获取网落图片资源
      * @param url
      * @return
      */
     public static Bitmap getHttpBitmap(String url){
        URL myFileURL;  
        Bitmap bitmap=null;  
        try{  
            myFileURL = new URL(url);  
            //获得连接  
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();  
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制  
            conn.setConnectTimeout(6000);  
            //连接设置获得数据流  
            conn.setDoInput(true);  
            //不使用缓存  
            conn.setUseCaches(false);  
            //这句可有可无，没有影响  
            //conn.connect();  
            //得到数据流  
            InputStream is = conn.getInputStream();  
            //解析得到图片  
            bitmap = BitmapFactory.decodeStream(is);  
            //关闭数据流  
            is.close();  
        }catch(Exception e){  
            e.printStackTrace();  
        }
        return bitmap;
    }  

}
