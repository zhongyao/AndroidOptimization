package com.hongri.optimization.blockcanary;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hongri.optimization.R;
import com.hongri.optimization.activity.MainActivity;

import java.util.List;

/**
 * 卡顿检测优化Activity【BlockCanary的运用】
 * 参考：https://www.jianshu.com/p/d172aafc3437
 */
public class BlockCanaryActivity extends AppCompatActivity implements View.OnClickListener {

    private Button testBlockBtn;
    private Button blockCanaryBtn;
    private String[] PERMISSIONS = {Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_canary);

        testBlockBtn = findViewById(R.id.testBlockBtn);
        blockCanaryBtn = findViewById(R.id.blockCanaryBtn);

        testBlockBtn.setOnClickListener(this);
        blockCanaryBtn.setOnClickListener(this);
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
            case R.id.blockCanaryBtn:
                XXPermissions.with(this).permission(PERMISSIONS).request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            testBlock();
                            toast("已成功获取所有权限");
                        } else {
                            toast("获取部分权限成功，但部分权限未正常授予");
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            toast("被永久拒绝授权，请手动授予相关权限");
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(BlockCanaryActivity.this, permissions);
                        } else {
                            toast("获取相关失败");
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}