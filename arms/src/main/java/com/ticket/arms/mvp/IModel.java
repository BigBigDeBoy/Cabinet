package com.ticket.arms.mvp;

/**
 * @author :dadade
 * date   :2019-05-27 11:20
 * desc   :框架要求框架中的每个 Model 都需要实现此类,以满足规范
 */
public interface IModel {
    /**
     *   {@link BasePresenter#onDestroy()} 时会默认调用 {@link IModel#onDestroy()}
     */
    void onDestroy();
}
