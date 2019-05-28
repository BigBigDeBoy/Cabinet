package com.ticket.arms.integration;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.ticket.arms.R;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import timber.log.Timber;

import static com.ticket.arms.base.Platform.DEPENDENCY_SUPPORT_DESIGN;

public final class AppManager {
    private static volatile AppManager sAppManager;
    protected final String TAG = this.getClass().getSimpleName();
    /**
     * true 为不需要加入到 Activity 容器进行统一管理,默认为 false
     */
    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_not_add_activity_list";
    private Application mApplication;
    private Activity mCurrentActivity;
    private List<Activity> mActivityList;

    public AppManager() {
    }

    public static AppManager getAppManager() {
        if (sAppManager == null) {
            synchronized (AppManager.class) {
                if (sAppManager == null) {
                    sAppManager = new AppManager();
                }
            }
        }
        return sAppManager;
    }

    public AppManager init(Application application) {
        mApplication = application;
        return this;
    }

    public void showSnackbar(String message, boolean isLong) {
        if (getCurrentActivity()==null&&getTopActivity()==null){
            return;
        }

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if (DEPENDENCY_SUPPORT_DESIGN){
                    Activity activity=getCurrentActivity()==null?getTopActivity():getCurrentActivity();
                    View view=activity.getWindow().getDecorView().findViewById(android.R.id.content);
                    Snackbar.make(view,message,isLong?Snackbar.LENGTH_LONG:Snackbar.LENGTH_SHORT).show();
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    public void setCurrentActivity(Activity activity) {
        this.mCurrentActivity = activity;
    }

    @Nullable
    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public List<Activity> getActivityList() {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        return mActivityList;
    }

    public Activity getTopActivity() {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when getTopActivity()");
            return null;
        }
        return mActivityList.size() > 0 ? mActivityList.get(mActivityList.size() - 1) : null;
    }

    public void addActivity(Activity activity){
        synchronized (AppManager.class){
            List<Activity> activities = getActivityList();
            if (!activities.contains(activity)){
                activities.add(activity);
            }
        }
    }

    /**
     * 删除集合里的指定的 {@link Activity} 实例
     *
     * @param {@link Activity}
     */
    public void removeActivity(Activity activity) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when removeActivity(Activity)");
            return;
        }
        synchronized (AppManager.class) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }


    public void killAll(){
        synchronized (AppManager.class){
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()){
                Activity next = iterator.next();
                iterator.remove();;
                next.finish();
            }
        }
    }

    public void startActivity(Intent intent) {
        if (getTopActivity()==null){
            Timber.tag(TAG).w("mCurrentActivity == null when startActivity(Intent)");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mApplication.startActivity(intent);
            return;
        }
        getTopActivity().startActivity(intent);
    }
}


