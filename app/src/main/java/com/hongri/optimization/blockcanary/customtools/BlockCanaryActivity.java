package com.hongri.optimization.blockcanary.customtools;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hongri.optimization.R;

public class BlockCanaryActivity extends AppCompatActivity implements View.OnClickListener {

    private Button testBlockBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_canary);

        testBlockBtn = findViewById(R.id.testBlockBtn);

        testBlockBtn.setOnClickListener(this);
    }

    /**
     * 模拟卡顿操作
     */
    private void testBlock() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.testBlockBtn:
                testBlock();
                break;
            default:
                break;
        }
    }
}