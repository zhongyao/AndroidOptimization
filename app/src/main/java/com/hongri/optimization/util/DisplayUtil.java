package com.hongri.optimization.util;

import android.app.Activity;
import android.util.Log;
import android.view.Display;

public class DisplayUtil {
    private static final String TAG = "DisplayUtil";

    /**
     * 获取屏幕主频频率
     * 一般是60HZ，现在的高端手机有出现90HZ或120HZ的
     *
     * @return
     */
    public static float getRefreshRate(Activity activity) {
        if (activity == null) {
            return 60.0f;
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        float refreshRate = display.getRefreshRate();
        Log.d(TAG, "屏幕主频频率 =" + refreshRate);
        return refreshRate;
    }
}
