package com.ticket.cabinet.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.ticket.arms.http.GlobalHttpHandler;
import com.ticket.arms.http.log.RequestInterceptor;
import com.ticket.arms.utils.ArmsUtils;
import com.ticket.cabinet.mvp.model.entity.User;

import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class GlobalHttpHandlerImpl implements GlobalHttpHandler {


    private Context context;

    public GlobalHttpHandlerImpl(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Response onHttpResultResponse(@NonNull String httpResult, @NonNull Interceptor.Chain chain, @NonNull Response response) {
        if (!TextUtils.isEmpty(httpResult) && RequestInterceptor.isJson(response.body().contentType())) {
            try {
                List<User> list = ArmsUtils.obtainAppComponentFromContext(context).gson().fromJson(httpResult, new TypeToken<List<User>>() {
                }.getType());
                User user = list.get(0);
                Timber.w("Result ------> " + user.getLogin() + "    ||   Avatar_url------> " + user.getAvatarUrl());

            } catch (Exception e) {
                e.printStackTrace();
                return response;
            }
        }


        return response;
    }

    @NonNull
    @Override
    public Request onHttpRequestBefore(@NonNull Interceptor.Chain chain, @NonNull Request request) {
        return request;
    }
}
