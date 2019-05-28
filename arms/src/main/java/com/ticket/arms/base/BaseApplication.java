package com.ticket.arms.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ticket.arms.base.delegate.AppDelegate;
import com.ticket.arms.di.component.AppComponent;
import com.ticket.arms.utils.Preconditions;

/**
 * @author :dadade
 * date   :2019-05-28 15:40
 * desc   :
 */
public class BaseApplication extends Application implements App {

    private AppDelegate mAppDelegate;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mAppDelegate==null){
            mAppDelegate = new AppDelegate(base);
        }
        this.mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mAppDelegate!=null){
            this.mAppDelegate.onCreate(this);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate!=null){
            this.mAppDelegate.onTerminate(this);
        }
    }

    @NonNull
    @Override
    public AppComponent getAppComponent() {
        Preconditions.checkNotNull(mAppDelegate, "%s cannot be null", AppDelegate.class.getName());
        Preconditions.checkState(mAppDelegate instanceof App, "%s must be implements %s", mAppDelegate.getClass().getName(), App.class.getName());
        return ((App) mAppDelegate).getAppComponent();
    }
}
