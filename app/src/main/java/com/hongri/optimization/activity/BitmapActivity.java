package com.hongri.optimization.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hongri.optimization.R;

/**
 * @description: 检测项目中不合理的图片[Bitmap]
 * @reference:
 * https://github.com/smallnew/BitmapCanary
 *
 * https://github.com/simplepeng/BitmapCanary
 *
 */
public class BitmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
    }
}