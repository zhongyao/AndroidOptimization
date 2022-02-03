package com.hongri.optimization;

import android.app.Application;
import android.util.Log;

import com.github.moduth.blockcanary.BlockCanary;
import com.hongri.optimization.blockcanary.customtools.BlockDetectByPrinter;
import com.hongri.optimization.blockcanary.realtools.AppBlockCanaryContext;

public class OptimizationApplication extends Application {

    private final String TAG = "OptimizationApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        //检测主线程耗时【自定义仿BlockCanary】
//        BlockDetectByPrinter.start();

        //检测主线程耗时【BlockCanary】
        BlockCanary.install(this, new AppBlockCanaryContext()).start();

    }
}
