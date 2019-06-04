package com.ticket.cabinet.mvp.presenter;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.widget.RecyclerView;

import com.ticket.arms.di.scope.ActivityScope;
import com.ticket.arms.http.imageloader.ImageLoader;
import com.ticket.arms.integration.AppManager;
import com.ticket.arms.mvp.BasePresenter;
import com.ticket.arms.utils.PermissionUtil;
import com.ticket.arms.utils.RxLifecycleUtils;
import com.ticket.cabinet.mvp.contract.MainContract;
import com.ticket.cabinet.mvp.model.entity.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    List<User> mUsers;
    @Inject
    RecyclerView.Adapter mAdapter;
    @Inject
    AppManager mAppManager;
    private int lastUserId;
    private boolean isFirst;
    private int preEndIndex;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        requestUsers(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
        this.mAppManager = null;
    }

    public void requestUsers(boolean pullToRefresh) {
        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                requestFromModel(pullToRefresh);
            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                mRootView.showMessage("Request permissions failure");
                mRootView.hideLoading();//隐藏下拉刷新的进度条
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                mRootView.showMessage("Need to go to the settings");
                mRootView.hideLoading();//隐藏下拉刷新的进度条
            }
        }, mRootView.getRxPermissions(), mErrorHandler);

    }

    private void requestFromModel(boolean pullToRefresh) {
        if (pullToRefresh) {
            lastUserId = 1;
        }
        boolean isEvictCache = pullToRefresh;
        if (pullToRefresh && isFirst) {
            isFirst = false;
            isEvictCache = false;
        }
        mModel.getUsers(lastUserId, isEvictCache)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> {
                    if (pullToRefresh) {
                        mRootView.showLoading();
                    } else {
                        mRootView.startLoadMore();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (pullToRefresh) {
                        mRootView.hideLoading();
                    } else {
                        mRootView.endLoadMore();
                    }
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<User>>(mErrorHandler) {
                    @Override
                    public void onNext(List<User> users) {
                        lastUserId = users.get(users.size() - 1).getId();
                        if (pullToRefresh) {
                            mUsers.clear();
                        }
                        preEndIndex = mUsers.size();
                        mUsers.addAll(users);
                        if (pullToRefresh) {
                            mAdapter.notifyDataSetChanged();
                        } else {
                            mAdapter.notifyItemRangeChanged(preEndIndex, users.size());
                        }
                    }
                });
    }
}
