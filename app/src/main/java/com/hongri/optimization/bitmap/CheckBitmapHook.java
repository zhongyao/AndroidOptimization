package com.hongri.optimization.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.taobao.android.dexposed.XC_MethodHook;

/**
 * @author：zhongyao
 * @date：2023/3/20
 * @description：ARTHook方式检测不合理图片
 */
public class CheckBitmapHook extends XC_MethodHook {
    private static final String TAG = "CheckBitmapHook";

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);

        ImageView imageView = (ImageView) param.thisObject;
        checkBitmap(imageView, imageView.getDrawable());
    }

    private static void checkBitmap(Object o, Drawable drawable) {
        if (drawable instanceof BitmapDrawable && o instanceof View) {
            final Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                final View view = (View) o;
                int width = view.getWidth();
                int height = view.getHeight();
                if (width > 0 && height > 0) {
                    if (bitmap.getWidth() > (width << 1) && bitmap.getHeight() > (height << 1)) {
                        warn(bitmap.getWidth(), bitmap.getHeight(), width, height,
                                new RuntimeException("Bitmap size is too large"));
                    }
                } else {
                    final Throwable stacktrace = new RuntimeException();
                    view.getViewTreeObserver().addOnPreDrawListener(
                            new ViewTreeObserver.OnPreDrawListener() {
                                @Override
                                public boolean onPreDraw() {
                                    int w = view.getWidth();
                                    int h = view.getHeight();
                                    if (w > 0 && h > 0) {
                                        if (bitmap.getWidth() >= (w << 1)
                                                && bitmap.getHeight() >= (h << 1)) {
                                            warn(bitmap.getWidth(), bitmap.getHeight(), w, h, stacktrace);
                                        }
                                        view.getViewTreeObserver().removeOnPreDrawListener(this);
                                    }
                                    return true;
                                }
                            });
                }
            }
        }
    }

    private static void warn(int bitmapWidth, int bitmapHeight, int viewWidth, int viewHeight, Throwable t) {
        String warnInfo = new StringBuilder("Bitmap size too large: ")
                .append("\n real size: (").append(bitmapWidth).append(',').append(bitmapHeight).append(')')
                .append("\n desired size: (").append(viewWidth).append(',').append(viewHeight).append(')')
                .append("\n call stack trace: \n").append(Log.getStackTraceString(t)).append('\n')
                .toString();


        Log.w(TAG, warnInfo);
    }
}
