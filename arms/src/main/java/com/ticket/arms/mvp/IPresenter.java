package com.ticket.arms.mvp;

import android.app.Activity;

/**
 * @author :dadade
 * date   :2019-05-27 11:18
 * desc   :
 */
public interface IPresenter {
    /**
     * 做一些初始化操作
     */
    void onStart();

    /**
     * {@link Activity#onDestroy()} 时会默认调用 {@link IPresenter#onDestroy()}
     */
    void onDestroy();
}
