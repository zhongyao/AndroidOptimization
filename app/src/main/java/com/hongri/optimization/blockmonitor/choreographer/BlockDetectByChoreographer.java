package com.hongri.optimization.blockmonitor.choreographer;

import android.util.Log;
import android.view.Choreographer;
import android.view.Display;

import com.hongri.optimization.blockmonitor.customtools.LogMonitor;

/**
 * 界面的显示：
 * CPU的计算 ---> GPU栅格化 ---> 设备显示
 * 参考：<a href="https://cloud.tencent.com/developer/article/1419031">...</a>
 * 参考：<a href="https://blog.csdn.net/lmj623565791/article/details/58626355">...</a>
 */
public class BlockDetectByChoreographer {
    private static String TAG = "性能检测";

    public static void start(float refreshRate) {
        //设置方法耗时卡口 TIME_BLOCK
        LogMonitor.getInstance().setTimeBlock(16);

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            private long lastTime = 0;

            @Override
            public void doFrame(long frameTimeNanos) {
                Log.d(TAG, "doFrame");
                if (lastTime == 0) {
                    //代码第一次初始化。不做检测统计。
                    lastTime = frameTimeNanos;
                } else {
                    //纳秒(ns)转换成毫秒(ms)
                    long times = (frameTimeNanos - lastTime) / 1000000;
                    //计算丢帧数
                    int dropFrames = (int) (times / (1000 / refreshRate));
                    if (times > 16) {
                        Log.e(TAG, "UI线程超时(超过16ms):" + times + "ms" + " , 丢帧:" + dropFrames);
                    }
                    lastTime = frameTimeNanos;
                }

                //以下方法用于收集具体卡顿日志--BEGIN
                if (LogMonitor.getInstance().isMonitor()) {
                    LogMonitor.getInstance().removeMonitor();
                }
                LogMonitor.getInstance().startMonitor();
                //END
                Choreographer.getInstance().postFrameCallback(this);
            }
        });
    }
}
