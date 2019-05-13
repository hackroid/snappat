/*
 * Created by Snooow on 2019/5/10
 */


package com.seclass.snappat.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/**
 * 杀死进程自我重启
 */
public class KillSelfService extends Service {
    private Handler handler;
    private String PackageName;
    public KillSelfService() {
        handler=new Handler();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        /*关闭应用后多久重新启动*/
        long stopDelayed = intent.getLongExtra("Delayed", 10);
        PackageName=intent.getStringExtra("PackageName");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent LaunchIntent = KillSelfService.this.getPackageManager().getLaunchIntentForPackage(PackageName);
                KillSelfService.this.startActivity(LaunchIntent);
                KillSelfService.this.stopSelf();
            }
        }, stopDelayed);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
