package com.hongri.optimization;

import android.app.Application;
import android.util.Log;

import com.hongri.optimization.blockcanary.customtools.BlockDetectByPrinter;

public class OptimizationApplication extends Application {

    private final String TAG = "OptimizationApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        //检测主线程耗时
        BlockDetectByPrinter.start();
    }
}
