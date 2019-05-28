package com.ticket.arms.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.ticket.arms.base.App;
import com.ticket.arms.di.component.AppComponent;
import com.ticket.arms.integration.AppManager;

/**
 * 一些框架常用的工具
 */
public class ArmsUtils {

    public static AppComponent obtainAppComponentFromContext(Context context){
        Preconditions.checkNotNull(context, "%s cannot be null", Context.class.getName());
        Preconditions.checkState(context.getApplicationContext() instanceof App, "%s must be implements %s", context.getApplicationContext().getClass().getName(), App.class.getName());
        return ((App)context.getApplicationContext()).getAppComponent();
    }

    public static void startActivity(Intent content){
        AppManager.getAppManager().startActivity(content);
    }

    public static void snackbarText(String text){
        AppManager.getAppManager().showSnackbar(text,false);
    }

    public static void configRecyclerView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
