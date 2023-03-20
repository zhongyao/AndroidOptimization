package com.hongri.optimization;

import android.app.Application;
import android.util.Log;
import hexin.androidbitmapcanary.ActivityDrawableWatcher;

public class OptimizationApplication extends Application {

    private final String TAG = "OptimizationApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        //检测主线程耗时【自定义仿BlockCanary】
//        BlockDetectByPrinter.start();

        //检测主线程耗时【BlockCanary】
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();

        /**
         * 【暂缺少so库会crash，后续处理 -- 对应CheckBitmapHook类】使用ARTHook方式检测不合理图片 注入Hook初始化
         */
//        DexposedBridge.hookAllConstructors(ImageView.class, new CheckBitmapHook() {
//            @Override protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//                DexposedBridge.findAndHookMethod(ImageView.class,"setImageBitmap", Bitmap.class,
//                        new CheckBitmapHook());
//            }
//        });

        //三方BitmapCanary 检测不合理图片【大图】
        ActivityDrawableWatcher.watchDrawable(this);

    }
}
