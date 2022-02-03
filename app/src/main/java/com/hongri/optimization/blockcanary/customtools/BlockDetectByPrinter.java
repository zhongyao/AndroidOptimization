package com.hongri.optimization.blockcanary.customtools;

import android.os.Looper;
import android.util.Log;
import android.util.Printer;

/**
 * 卡顿检测类
 */
public class BlockDetectByPrinter {
    private static final String TAG = "BlockDetectByPrinter";
    public static void start() {
        Looper.getMainLooper().setMessageLogging(new Printer() {
            //分发和处理消息开始前的log
            private static final String START = ">>>>> Dispatching";
            //分发和处理消息结束后的log
            private static final String END = "<<<<< Finished";
            @Override
            public void println(String x) {
                if (x.startsWith(START)) {
                    //开始计时
                    Log.d(TAG, "dispatch start ---> x:" + x);
                    LogMonitor.getInstance().startMonitor();
                }
                if (x.startsWith(END)) {
                    //结束计时，并计算出方法执行时间
                    Log.d(TAG, "dispatch end ---> x:" + x);
                    LogMonitor.getInstance().removeMonitor();
                }
            }
        });
    }
}
