package com.ticket.cabinet.mvp.contract;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ticket.arms.mvp.IModel;
import com.ticket.arms.mvp.IView;
import com.ticket.cabinet.mvp.model.entity.User;

import java.util.List;

import io.reactivex.Observable;

public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        RxPermissions getRxPermissions();

        void startLoadMore();

        void endLoadMore();

        Activity getActivity();

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable<List<User>> getUsers(int lastUserId, boolean update);
    }
}
