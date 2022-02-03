package com.hongri.optimization.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hongri.optimization.R;

/**
 * 渲染分析：
 * 参考：https://developer.android.com/studio/profile/inspect-gpu-rendering#profile_rendering
 *
 * Swap Buffers：
 * Represents the time the CPU is waiting for the GPU to finish its work. If this bar gets tall, it means the app is doing too much work on the GPU.
 *
 * Command Issue：
 * Represents the time spent by Android's 2D renderer issuing commands to OpenGL to draw and redraw display lists. The height of this bar is directly proportional to the sum of the time it takes each display list to execute—more display lists equals a taller red bar.
 *
 * Sync & Upload：
 * Represents the time it take to upload bitmap information to the GPU. A large segment indicates that the app is taking considerable time loading large amounts of graphics.
 *
 * Draw：
 * Represents the time used to create and update the view's display lists. If this part of the bar is tall, there may be a lot of custom view drawing, or a lot of work in onDraw methods.
 *
 * Measure / Layout：
 * Represents the amount of time spent on onLayout and onMeasure callbacks in the view hierarchy. A large segment indicates that the view hierarchy is taking a long time to process.
 *
 * Animation：
 * Represents the time it took to evaluate all the animators that were running that frame. If this segment is large, your app could be using a custom animator that is not performing well or some unintended work is happening as a result of properties being updated.
 *
 * Input Handling：
 * Represents the time that the app spent executing code inside of an input event callback. If this segment is large it indicates that the app is spending too much time processing the user input. Consider offloading such processing to a different thread.
 *
 * Misc Time / VSync Delay：
 * Represents the time that the app spends executing operations in between two consecutive frames. It might be an indicator of too much processing happening in the UI thread that could be offloaded to a different thread.
 *
 *
 */
public class RenderingAnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendering_analysis);
    }
}
