package com.ticket.arms.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticket.arms.integration.ActivityLifecycle;
import com.ticket.arms.integration.AppManager;
import com.ticket.arms.integration.FragmentLifecycle;
import com.ticket.arms.integration.IRepositoryManager;
import com.ticket.arms.integration.RepositoryManager;
import com.ticket.arms.integration.cache.Cache;
import com.ticket.arms.integration.cache.CacheType;
import com.ticket.arms.integration.lifecycle.ActivityLifecycleForRxLifecycle;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * 提供一些框架必须的实例的 {@link Module}
 */
@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static Gson provideGson(Application application, @Nullable GsonConfiguration configuration) {
        GsonBuilder builder = new GsonBuilder();
        if (configuration != null) {
            configuration.configGson(application, builder);
        }
        return builder.create();
    }

    @Binds
    @Named("ActivityLifecycle")
    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycle(ActivityLifecycle activityLifecycle);

    @Binds
    @Named("ActivityLifecycleForRxLifecycle")
    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycleForRxLifecycle(ActivityLifecycleForRxLifecycle activityLifecycleForRxLifecycle);

    @Binds
    abstract FragmentManager.FragmentLifecycleCallbacks bindFragmentLifecycle(FragmentLifecycle fragmentLifecycle);

    @Singleton
    @Provides
    static List<FragmentManager.FragmentLifecycleCallbacks> provideFragmentLifecycles(){
        return new ArrayList<>();
    }

    @Singleton
    @Provides
    static AppManager provideAppManager(Application application) {
        return AppManager.getAppManager().init(application);
    }

    @Binds
    abstract IRepositoryManager bindRepositoryManager(RepositoryManager repositoryManager);

    @Singleton
    @Provides
    static Cache<String,Object> provideExtras(Cache.Factory cacheFactory){
        return cacheFactory.build(CacheType.EXTRAS);
    }

    public interface GsonConfiguration {
        void configGson(@NotNull Context context, @NotNull GsonBuilder builder);
    }
}
