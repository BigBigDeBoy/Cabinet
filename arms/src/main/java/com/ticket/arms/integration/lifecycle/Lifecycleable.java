package com.ticket.arms.integration.lifecycle;

import org.jetbrains.annotations.NotNull;

import io.reactivex.subjects.Subject;


public interface Lifecycleable<E> {
    @NotNull
    Subject<E> provideLifecycleSubject();
}
