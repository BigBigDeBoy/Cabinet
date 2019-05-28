package com.ticket.arms.base.delegate;

import android.app.Activity;
import android.os.Bundle;

import com.ticket.arms.di.component.AppComponent;
import com.ticket.arms.integration.cache.Cache;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 框架要求框架中的每个 {@link Activity} 都需要实现此类,以满足规范
 */
public interface IActivity {
    @NotNull
    Cache<String,Object> provideCache();

    void setupActivityComponent(@NotNull AppComponent appComponent);

    boolean useEventBus();

    int initView(@Nullable Bundle savedInstanceState);

    void initData(@Nullable Bundle savedInstanceState);


    boolean useFragment();




}
