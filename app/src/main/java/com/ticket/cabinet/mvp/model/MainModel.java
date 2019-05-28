package com.ticket.cabinet.mvp.model;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.google.gson.Gson;
import com.ticket.arms.di.scope.ActivityScope;
import com.ticket.arms.integration.IRepositoryManager;
import com.ticket.arms.mvp.BaseModel;
import com.ticket.cabinet.mvp.contract.MainContract;
import com.ticket.cabinet.mvp.model.api.cache.CommonCache;
import com.ticket.cabinet.mvp.model.api.service.UserService;
import com.ticket.cabinet.mvp.model.entity.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import timber.log.Timber;

@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {

    public static final int USERS_PER_PAGE = 10;

    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<List<User>> getUsers(int lastIdQueried, boolean update) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(UserService.class)
                .getUsers(lastIdQueried, USERS_PER_PAGE))
                .flatMap(new Function<Observable<List<User>>, ObservableSource<List<User>>>() {
                    @Override
                    public ObservableSource<List<User>> apply(Observable<List<User>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getUsers(listObservable, new DynamicKey(lastIdQueried), new EvictDynamicKey(update))
                                .map(listReply -> listReply.getData());
                    }
                });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        Timber.d("Release Resource");
    }
}