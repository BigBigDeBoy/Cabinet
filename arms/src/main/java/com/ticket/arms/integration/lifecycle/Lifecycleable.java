package com.ticket.arms.integration.lifecycle;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.trello.rxlifecycle2.RxLifecycle;

import org.jetbrains.annotations.NotNull;

import io.reactivex.subjects.Subject;

/**
 *  让 {@link Activity}/{@link Fragment} 实现此接口,即可正常使用 {@link RxLifecycle}
 *  无需再继承 {@link RxLifecycle} 提供的 Activity/Fragment ,扩展性极强
 * @param <E>
 */
public interface Lifecycleable<E> {
    @NotNull
    Subject<E> provideLifecycleSubject();
}
