package com.ticket.arms.mvp;

import android.content.Intent;

import com.ticket.arms.utils.ArmsUtils;

import org.jetbrains.annotations.NotNull;

import static com.ticket.arms.utils.Preconditions.checkNotNull;

public interface IView {
    /**
     * 显示加载
     */
   default void showLoading(){

   }

    /**
     * 隐藏加载
     */
   default void hideLoading(){

   }

    /**
     * 显示信息
     * @param message
     */
   void showMessage(@NotNull String message);


   default void launchActivity(@NotNull Intent intent){
       checkNotNull(intent);
       ArmsUtils.startActivity(intent);
    }

    /**
     * 杀死自己
     */
    default void killMyself() {

    }

}
