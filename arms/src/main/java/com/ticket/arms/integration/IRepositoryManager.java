package com.ticket.arms.integration;

import android.content.Context;
import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

/**
 * @author :dadade
 * date   :2019-05-27 16:08
 * desc   :
 */
public interface IRepositoryManager {

    /**
     * 根据传入的 Class 获取对应的 Retrofit service
     * @param service
     * @param <T>
     * @return
     */
    @NotNull
    <T>T obtainRetrofitService(@NotNull Class<T> service);

    /**
     * 根据传入的 Class 获取对应的 RxCache service
     * @param service
     * @param <T>
     * @return
     */
    @NotNull
    <T>T obtainCacheService(@NotNull Class<T> service);

    /**
     * 清理所有缓存
     */
    void clearAllCache();

    /**
     * 获取 {@link Context}
     *
     * @return {@link Context}
     */
    @NonNull
    Context getContext();
}
