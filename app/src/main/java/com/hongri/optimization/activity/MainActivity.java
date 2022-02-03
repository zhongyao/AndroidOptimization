package com.hongri.optimization.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hongri.optimization.R;
import com.hongri.optimization.blockcanary.BlockCanaryActivity;
import com.hongri.optimization.leakcanary.LeakCanaryActivity;

/**
 * @author hongri
 *
 *         Android优化包含如下几个部分：
 *         一、布局优化：
 *         1、如果布局中既可以采用RelativeLayout，也可以采用LinearLayout，建议使用LinearLayout（FrameLayout）。
 *         2、尽量减少布局的层次
 *         3、采用<include>标签、<merge>标签和ViewStub：
 *         <include>标签主要用于布局重用。
 *         <merge>标签一般和<include>配合使用，可以减少布局的层级。
 *         ViewStub提供了按需加载的功能。
 *
 *         二、绘制优化：指View的onDraw方法中要避免执行耗时的操作（建议保持60fps绘制帧率）。
 *         1、onDraw不要创建新的的局部对象，这是因为onDraw方法可能会被重复调用，这样一瞬间就会产生大量的临时对象，这样不仅耗费大量的内存
 *         还会导致系统频繁GC，降低了程序的执行效率。
 *         2、onDraw中不要做耗时操作。
 *
 *         三、内存泄漏优化：
 *         1、静态变量导致的内存泄漏
 *         2、单例模式导致的内存泄漏
 *         3、属性动画导致的内存泄漏
 *
 *         四、RecyclerView优化：
 *         1、根据列表的滑动状态，来控制任务的执行频率
 *         2、开启硬件加速
 *
 *         五、Bitmap优化：
 *         1、对图片采样处理
 *
 *         六、线程优化：
 *         1、采用线程池：采用线程池可以重用内部的线程，从而避免了线程创建和销毁所带来的开销，同时线程池还能够有效控制线程池的最大并发数
 *         避免大量的线程因为抢占系统资源而导致的阻塞现象的发生。
 *
 *         七、其他性能优化建议：
 *         1、避免创建过多的对象。
 *         2、不要过多使用枚举，枚举占用的内存空间比整形大。
 *         3、常量使用static final来修饰。
 *         4、采用二级缓存策略
 *         5、尽量采用静态内部类，这样可以避免潜在的由于内部类而导致的内存泄漏。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private Button overdrawBtn;
    private Button constraintLayoutBtn;
    private Button renderingAnalysisBtn;
    private Button leakCanaryBtn;
    private Button blockCanaryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        overdrawBtn = findViewById(R.id.overdrawBtn);
        constraintLayoutBtn = findViewById(R.id.constraintLayoutBtn);
        renderingAnalysisBtn = findViewById(R.id.renderingAnalysisBtn);
        leakCanaryBtn = findViewById(R.id.leakCanaryBtn);
        blockCanaryBtn = findViewById(R.id.blockCanaryBtn);

        btn.setOnClickListener(this);
        overdrawBtn.setOnClickListener(this);
        constraintLayoutBtn.setOnClickListener(this);
        renderingAnalysisBtn.setOnClickListener(this);
        leakCanaryBtn.setOnClickListener(this);
        blockCanaryBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.overdrawBtn:
                Intent intentOverdraw = new Intent(this, OverdrawActivity.class);
                startActivity(intentOverdraw);
                break;
            case R.id.constraintLayoutBtn:
                Intent intentConstraintLayout = new Intent(this, ConstraintLayoutActivity.class);
                startActivity(intentConstraintLayout);
                break;
            case R.id.renderingAnalysisBtn:
                Intent intentRenderingAnalysis = new Intent(this, RenderingAnalysisActivity.class);
                startActivity(intentRenderingAnalysis);
                break;
            case R.id.leakCanaryBtn:
                Intent intentLeakCanary = new Intent(this, LeakCanaryActivity.class);
                startActivity(intentLeakCanary);
                break;
            case R.id.blockCanaryBtn:
                Intent intentBlockCanary = new Intent(this, BlockCanaryActivity.class);
                startActivity(intentBlockCanary);
                break;
            default:
                break;
        }
    }
}
