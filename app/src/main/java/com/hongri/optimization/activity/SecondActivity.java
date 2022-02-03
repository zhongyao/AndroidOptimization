package com.hongri.optimization.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hongri.optimization.R;

public class SecondActivity extends AppCompatActivity {
    List<ImageView> imageViews = new ArrayList<>();
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /**
         * 内存泄漏测试代码
         */
        // 此activity持有10000个view对象
        for (int i = 0; i < 10000; i++) {
            imageViews.add(new ImageView(this));
        }

        mHandler = new Handler();
        // 100秒后执行此Runnable
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 100 * 1000);
    }
}
