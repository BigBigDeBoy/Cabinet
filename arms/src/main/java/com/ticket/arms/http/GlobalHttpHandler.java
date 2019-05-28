package com.ticket.arms.http;

import android.support.annotation.NonNull;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public interface GlobalHttpHandler {

    @NonNull
    Response onHttpResultResponse(@NonNull String httpResult, @NonNull Interceptor.Chain chain,@NonNull Response response);

    @NonNull
    Request onHttpRequestBefore(@NonNull Interceptor.Chain chain, @NonNull Request request);




}
