package com.hongri.optimization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 测试过度绘制Activity：
 * 没有过度绘制：白色
 * 过度绘制一次：蓝色
 * 过度绘制两次：绿色
 * 过度绘制三次：粉红色
 * 过度绘制四次：红色
 *
 *
 * 找到过度绘制的方法：
 * Layout Inspector工具来遍历视图层级
 *
 * 解决过度绘制的方法：
 * 1、去除不必要的背景色
 * 2、扁平化布局
 * 3、降低透明度
 *
 */
public class OverdrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdraw);
    }
}
