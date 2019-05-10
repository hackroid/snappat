/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.utils;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.seclass.snappat.R;

public class ThemeUtil {
    public ThemeUtil() {
    }

    public static void setTheme(@NonNull Activity activity) {
        activity.setTheme(Utils.getSpUtils().getString("loginmode") != null ? R.style.ThemeLight : R.style.ThemeDark);
    }

    public static void reCreate(@NonNull final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.recreate();
            }
        });

    }
}

