package com.ticket.arms.integration;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.ticket.arms.base.delegate.AppLifecycles;
import com.ticket.arms.di.module.GlobalConfigModule;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ConfigModule {

    void applyOptions(@NotNull Context context, @NotNull GlobalConfigModule.Builder builder);

    void injectAppLifecycle(@NotNull Context context, @NotNull List<AppLifecycles> lifecycles);

    void injectActivityLifecycle(@NotNull Context context, @NotNull List<Application.ActivityLifecycleCallbacks> lifecycles);

    void injectFragmentLifecycle(@NotNull Context context, @NotNull List<FragmentManager.FragmentLifecycleCallbacks> lifecycles);
}
