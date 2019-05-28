package com.ticket.arms.mvp;

/**
 * @author :dadade
 * date   :2019-05-27 11:20
 * desc   :
 */
public interface IModel {
    /**
     *   {@link BasePresenter#onDestroy()} 时会默认调用 {@link IModel#onDestroy()}
     */
    void onDestroy();
}
