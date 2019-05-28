package com.ticket.arms.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.view.View;

import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.jetbrains.annotations.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.ticket.arms.base.Platform.DEPENDENCY_AUTO_LAYOUT;
import static com.ticket.arms.base.delegate.ActivityDelegate.LAYOUT_FRAMELAYOUT;
import static com.ticket.arms.base.delegate.ActivityDelegate.LAYOUT_LINEARLAYOUT;
import static com.ticket.arms.base.delegate.ActivityDelegate.LAYOUT_RELATIVELAYOUT;

public class ThirdViewUtil {

    private static int HAS_AUTO_LAYOUT_META = -1;//0 说明 AndroidManifest 里面没有使用 AutoLauout 的 Meta, 即不使用 AutoLayout, 1 为有 Meta, 即需要使用

    @Nullable
    public static View convertAutoView(String name, Context context, AttributeSet attrs) {
        if (!DEPENDENCY_AUTO_LAYOUT) {
            return null;
        }

        if (HAS_AUTO_LAYOUT_META == -1) {
            HAS_AUTO_LAYOUT_META = 1;
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo;
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo == null || applicationInfo.metaData == null
                        || !applicationInfo.metaData.containsKey("design_width")
                        || !applicationInfo.metaData.containsKey("design_height")) {
                    HAS_AUTO_LAYOUT_META = 0;
                }
            } catch (PackageManager.NameNotFoundException e) {
                HAS_AUTO_LAYOUT_META = 0;
            }
        }

        if (HAS_AUTO_LAYOUT_META == 0) {
            return null;
        }
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        } else if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        } else if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }
        return view;

    }

    public static boolean isUseAutolayout() {
        return DEPENDENCY_AUTO_LAYOUT && HAS_AUTO_LAYOUT_META == 1;
    }

    public static Unbinder bindTarget(Object target, Object source) {
        if (source instanceof Activity) {
            return ButterKnife.bind(target, (Activity) source);
        } else if (source instanceof View) {
            return ButterKnife.bind(target, (View) source);
        } else if (source instanceof Dialog) {
            return ButterKnife.bind(target, (Dialog) source);
        } else {
            return Unbinder.EMPTY;
        }
    }
}
