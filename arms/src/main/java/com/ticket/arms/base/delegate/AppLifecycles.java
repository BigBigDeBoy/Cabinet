package com.ticket.arms.base.delegate;

import android.app.Application;
import android.content.Context;

import org.jetbrains.annotations.NotNull;

/**
 * 用于代理 {@link Application} 的生命周期
 */
public interface AppLifecycles {
    void attachBaseContext(@NotNull Context base);

    void onCreate(@NotNull Application application);

    void onTerminate(@NotNull Application application);
}
