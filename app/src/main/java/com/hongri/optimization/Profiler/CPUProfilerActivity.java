package com.hongri.optimization.Profiler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.hongri.optimization.R;

/**
 * CPU性能分析：
 * View-->Tool Windows-->Profiler
 */
public class CPUProfilerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpuprofiler);
    }
}
