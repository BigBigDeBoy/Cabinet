package com.ticket.arms.base.delegate;

import android.os.Bundle;

import com.ticket.arms.di.component.AppComponent;
import com.ticket.arms.integration.cache.Cache;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IActivity {
    @NotNull
    Cache<String,Object> provideCache();

    void setupActivityComponent(@NotNull AppComponent appComponent);

    boolean useEventBus();

    int initView(@Nullable Bundle savedInstanceState);

    void initData(@Nullable Bundle savedInstanceState);


    boolean useFragment();




}
