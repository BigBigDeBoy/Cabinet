package com.ticket.cabinet.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;


public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (!activity.getIntent().getBooleanExtra("isInitToolbar", false)) {
            activity.getIntent().putExtra("isInitToolbar", true);
//            if (activity.findViewById(R.id.toolbar)!)


        }

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activity.getIntent().removeExtra("isInitToolbar");

    }
}
