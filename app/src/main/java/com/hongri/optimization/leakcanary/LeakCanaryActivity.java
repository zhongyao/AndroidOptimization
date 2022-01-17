package com.hongri.optimization.leakcanary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hongri.optimization.R;

/**
 * 监控内存泄漏框架之LeakCanary,其工作原理是:
 *
 * 1、LeakCanary的检测机制，利用了Java的WeakReference和ReferenceQueue。
 * 通过将Activity包装到WeakReference中，被WeakReference包装过的Activity对象如果被回收，
 * 该WeakReference引用会被放到ReferenceQueue中，通过监测ReferenceQueue里面的内容就能检查到
 * Activity是否能够被回收（在ReferenceQueue中说明可以被回收，不存在泄漏。否则，可能存在泄漏，LeakCanary执行一遍GC，
 * 若还未在ReferenceQueue中，就会认定为泄漏）。
 *
 * 2、如果Activity被认定为泄漏了，就抓取内存dump文件(Debug.dumpHprofData)；之后通过HeapAnalyzerService.runAnalysis
 * 进行内存文件分析；接着通过HeapAnalyzer (checkForLeak—findLeakingReference—findLeakTrace)来进行内存泄漏分析。最后
 * 通过DisplayLeakService进行内存泄漏的展示。
 *
 */
public class LeakCanaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);
        //单例模式内存泄漏场景测试
        Singleton singleton = Singleton.newInstance(this);
    }
}