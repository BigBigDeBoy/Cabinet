package com.ticket.arms.http.imageloader;

import android.content.Context;
import android.support.annotation.Nullable;

import com.ticket.arms.utils.Preconditions;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ImageLoader {

    @Inject
    @org.jetbrains.annotations.Nullable
    public ImageLoader() {
    }

    @Inject
    @Nullable
    BaseImageLoaderStrategy mStrategy;

    @Nullable
    public BaseImageLoaderStrategy getLoadImgStrategy() {
        return mStrategy;
    }

    public <T extends ImageConfig> void loadImage(Context context, T config) {
        Preconditions.checkNotNull(mStrategy, "Please implement BaseImageLoaderStrategy and call GlobalConfigModule.Builder#imageLoaderStrategy(BaseImageLoaderStrategy) in the applyOptions method of ConfigModule");
        this.mStrategy.loadImage(context, config);
    }

    public <T extends ImageConfig> void clear(Context context, T config) {
        Preconditions.checkNotNull(context, config);
        this.mStrategy.clear(context, config);
    }
}
