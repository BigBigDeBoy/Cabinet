package com.ticket.cabinet.mvp.model.api.cache;

import com.ticket.cabinet.mvp.model.entity.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * @author :dadade
 * date   :2019-05-27 18:28
 * desc   :
 */
public interface CommonCache {
    @LifeCache(duration = 2,timeUnit = TimeUnit.SECONDS)
    Observable<Reply<List<User>>> getUsers(Observable<List<User>> users, DynamicKey idLastUserQueried , EvictProvider evictProvider);
}
