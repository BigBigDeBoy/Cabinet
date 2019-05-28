package com.ticket.arms.integration.cache;


import android.support.annotation.Nullable;

import com.ticket.arms.utils.Preconditions;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *  {@link IntelligentCache} 含有可将数据永久存储至内存中的存储容器 {@link #mMap}, 和当达到最大容量时可根据 LRU
 *  算法抛弃不合规数据的存储容器 {@link #mCache}
 *  {@link IntelligentCache} 可根据您传入的 {@code key} 智能的判断您需要将数据存储至哪个存储容器, 从而针对数据
 *  的不同特性进行不同的存储优化
 *  调用 {@link IntelligentCache#put(Object, Object)} 方法, 使用 {@link #KEY_KEEP} + {@code key} 作为 key 传入的
 *  {@code value} 可存储至 {@link #mMap} (数据永久存储至内存中, 适合比较重要的数据) 中, 否则储存至 {@link #mCache}
 *
 */
public class IntelligentCache<V> implements Cache<String, V> {

    private final Map<String, V> mMap;//可将数据永久存储至内存中的存储容器
    private final Cache<String, V> mCache;//当达到最大容量时可根据 LRU 算法抛弃不合规数据的存储容器
    public static final String KEY_KEEP = "Keep=";

    public IntelligentCache(int size) {
        this.mMap = new HashMap<>();
        this.mCache = new LruCache<>(size);
    }

    @NotNull
    public static String getKeyOfKeep(@NotNull String key) {
        Preconditions.checkNotNull(key,"key=null");
        return IntelligentCache.KEY_KEEP+key;
    }

    @Override
    public synchronized int size() {
        return mMap.size()+mCache.size();
    }

    @Override
    public synchronized int getMaxSize() {
        return mMap.size()+mCache.getMaxSize();
    }

    @Nullable
    @Override
    public  synchronized V get(String key) {
        if (key.startsWith(KEY_KEEP)){
            return mMap.get(key);
        }
        return mCache.get(key);
    }

    @Nullable
    @Override
    public synchronized V put(String key, V value) {
        if (key.startsWith(KEY_KEEP)){
            return mMap.put(key,value);
        }
        return mCache.put(key,value);
    }

    @Nullable
    @Override
    public synchronized V remove(String key) {
        if (key.startsWith(KEY_KEEP)){
            return mMap.remove(key);
        }
        return mCache.remove(key);
    }

    @Override
    public synchronized boolean containsKey(String key) {
        if (key.startsWith(KEY_KEEP)){
            return mMap.containsKey(key);
        }
        return mCache.containsKey(key);
    }

    @Override
    public synchronized Set<String> keySet() {
        Set<String> set = mCache.keySet();
        set.addAll(mMap.keySet());
        return set;
    }

    @Override
    public void clear() {
        mCache.clear();
        mMap.clear();
    }
}
