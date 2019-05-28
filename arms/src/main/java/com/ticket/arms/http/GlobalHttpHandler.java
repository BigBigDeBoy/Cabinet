package com.ticket.arms.http;

import android.support.annotation.NonNull;

import com.ticket.arms.di.module.GlobalConfigModule;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 处理 Http 请求和响应结果的处理类
 * 使用 {@link GlobalConfigModule.Builder#globalHttpHandler(GlobalHttpHandler)} 方法配置
 */
public interface GlobalHttpHandler {

    @NonNull
    Response onHttpResultResponse(@NonNull String httpResult, @NonNull Interceptor.Chain chain, @NonNull Response response);

    @NonNull
    Request onHttpRequestBefore(@NonNull Interceptor.Chain chain, @NonNull Request request);

}
