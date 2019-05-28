package com.ticket.arms.base.delegate;

import android.app.Application;
import android.content.Context;

import org.jetbrains.annotations.NotNull;

public interface AppLifecycles {
    void attachBaseContext(@NotNull Context base);

    void onCreate(@NotNull Application application);

    void onTerminate(@NotNull Application application);
}
