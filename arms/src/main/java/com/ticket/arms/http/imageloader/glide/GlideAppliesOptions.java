package com.ticket.arms.http.imageloader.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.ticket.arms.http.imageloader.BaseImageLoaderStrategy;

/**
 * 如果你想具有配置 @{@link Glide} 的权利,则需要让 {@link BaseImageLoaderStrategy}
 * 的实现类也必须实现 {@link GlideAppliesOptions}
 */
public interface GlideAppliesOptions {
    void applyGlideOptions(@NonNull Context context, @NonNull GlideBuilder builder);
}
