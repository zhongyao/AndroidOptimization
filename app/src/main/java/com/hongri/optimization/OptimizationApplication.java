package com.hongri.optimization;

import android.app.Application;
import android.util.Log;

public class OptimizationApplication extends Application {

    private final String TAG = "OptimizationApplication";
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");

    }
}
