package com.ticket.arms.base;


import android.support.annotation.NonNull;

import com.ticket.arms.di.component.AppComponent;

public interface App {
    @NonNull
    AppComponent getAppComponent();
}
