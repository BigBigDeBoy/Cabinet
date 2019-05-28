package com.ticket.arms.http.imageloader;

import android.content.Context;
import android.support.annotation.Nullable;

public interface BaseImageLoaderStrategy<T extends ImageConfig> {

    void loadImage(@Nullable Context context, @Nullable T config);

    void clear(@Nullable Context ctx, @Nullable T config);
}
