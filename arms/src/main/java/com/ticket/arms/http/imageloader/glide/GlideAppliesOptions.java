package com.ticket.arms.http.imageloader.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;

public interface GlideAppliesOptions {
    void applyGlideOptions(@NonNull Context context, @NonNull GlideBuilder builder);
}
