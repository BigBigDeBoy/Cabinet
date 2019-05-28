package com.ticket.cabinet.app;

import android.content.Context;

import com.ticket.arms.utils.ArmsUtils;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import retrofit2.HttpException;

/**
 * 展示 {@link ResponseErrorListener} 的用法
 */
public class ResponseErrorListenerImpl implements ResponseErrorListener {
    @Override
    public void handleResponseError(Context context, Throwable t) {
        String msg="未知错误";
        if (t instanceof UnknownHostException){
            msg="网络不可用";
        }else if (t instanceof SocketTimeoutException){
            msg="请求网络超时";
        }else if (t instanceof HttpException){
            HttpException httpException=(HttpException)t;
            msg = convertStatusCode(httpException);
        }
        ArmsUtils.snackbarText(msg);
    }

    private String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code()==500){
            msg="服务器发生错误";
        }else if (httpException.code()==404){
            msg="请求地址不存在";
        }else if (httpException.code()==403){
            msg="请求被服务器拒绝";
        }else if (httpException.code()==307){
            msg="请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }
}
