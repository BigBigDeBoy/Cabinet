package com.ticket.arms.base.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.ticket.arms.integration.EventBusManager;
import com.ticket.arms.utils.ArmsUtils;

import org.jetbrains.annotations.NotNull;

/**
 * {@link ActivityDelegate} 默认实现类
 */
public class ActivityDelegateImpl implements ActivityDelegate {


    private  IActivity iActivity;
    private Activity mActivity;

    public ActivityDelegateImpl(@NotNull Activity activity) {
        this.mActivity = activity;
        this.iActivity=(IActivity)activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (iActivity.useEventBus()){
            EventBusManager.getInstance().register(mActivity);
        }

        iActivity.setupActivityComponent(ArmsUtils.obtainAppComponentFromContext(mActivity));

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onDestroy() {

    }
}
