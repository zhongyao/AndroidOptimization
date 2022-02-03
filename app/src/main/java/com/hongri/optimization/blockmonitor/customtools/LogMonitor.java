package com.hongri.optimization.blockmonitor.customtools;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 这里利用了 HandlerThread 来检测耗时方法：
 * <p>
 * 在分发和处理消息开始前，发送一个延迟300毫秒的消息，
 * 如果分发和处理消息结束后还不到300毫秒，也就是消息处理时间小于300毫秒就会移除这个延迟300毫秒的消息，
 * 否则就会打印出这个耗时消息的栈轨迹。
 */
public class LogMonitor {
    private static final String TAG = "LogMonitor";
    private static LogMonitor sInstance = new LogMonitor();
    private Handler mIoHandler;
    //方法耗时的卡口,300毫秒
    private static long TIME_BLOCK = 300L;

    private LogMonitor() {
        HandlerThread logThread = new HandlerThread("log");
        logThread.start();
        mIoHandler = new Handler(logThread.getLooper());
    }

    private static Runnable mLogRunnable = new Runnable() {
        @Override
        public void run() {
            //打印出执行的耗时方法的栈消息
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            for (StackTraceElement s : stackTrace) {
                sb.append(s.toString());
                sb.append("\n");
            }
            //【这里运行在子线程不用担心卡顿问题，可以在合适的时机将相关log写到本地，然后上报到卡顿监控平台】
            Log.e(TAG, sb.toString());
        }
    };

    public static LogMonitor getInstance() {
        return sInstance;
    }

    public void setTimeBlock(long timeBlock) {
        TIME_BLOCK = timeBlock;
    }

    public boolean isMonitor() {
        Class handlerClass;
        Method method;
        try {
            handlerClass = Class.forName("android.os.Handler");
            method = handlerClass.getDeclaredMethod("hasCallbacks", Runnable.class);
            method.setAccessible(true);
            return (boolean) method.invoke(mIoHandler, mLogRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 开始计时
     */
    public void startMonitor() {
        Log.d(TAG, "startMonitor");
        mIoHandler.postDelayed(mLogRunnable, TIME_BLOCK);
    }

    /**
     * 停止计时
     */
    public void removeMonitor() {
        Log.d(TAG, "removeMonitor");
        mIoHandler.removeCallbacks(mLogRunnable);
    }
}
