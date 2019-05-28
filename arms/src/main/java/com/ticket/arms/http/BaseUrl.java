package com.ticket.arms.http;


import android.support.annotation.NonNull;

import okhttp3.HttpUrl;

public interface BaseUrl {
    @NonNull
    HttpUrl url();
}
