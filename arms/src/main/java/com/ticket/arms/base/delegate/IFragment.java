package com.ticket.arms.base.delegate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ticket.arms.di.component.AppComponent;
import com.ticket.arms.integration.cache.Cache;

import org.jetbrains.annotations.NotNull;

/**
 * @author :dadade
 * date   :2019-05-28 10:59
 * desc   :
 */
public interface IFragment {

    @NotNull
    Cache<String, Object> provideCache();

    /**
     * 提供 AppComponent (提供所有的单例对象)给实现类,进行 Component 依赖
     *
     * @param appComponent
     */
    void setupFragmentComponent(@NotNull AppComponent appComponent);


    boolean useEventBus();

    View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    void initData(@NotNull Bundle savedInstanceState);


}
