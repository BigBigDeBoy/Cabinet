package com.ticket.cabinet.di.module;


import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ticket.arms.di.scope.ActivityScope;
import com.ticket.cabinet.mvp.contract.MainContract;
import com.ticket.cabinet.mvp.model.MainModel;
import com.ticket.cabinet.mvp.model.entity.User;
import com.ticket.cabinet.mvp.ui.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainModule {

    @Binds
    abstract MainContract.Model bindMainModel(MainModel model);


    @ActivityScope
    @Provides
    static RxPermissions provideRxPermissions(MainContract.View view) {
        return new RxPermissions((FragmentActivity) view.getActivity());
    }


    @ActivityScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(MainContract.View view) {
        return new GridLayoutManager(view.getActivity(), 2);
    }

    @ActivityScope
    @Provides
    static RecyclerView.Adapter provideUserAdapter(List<User> list) {
        return new UserAdapter(list);
    }

    @ActivityScope
    @Provides
    static List<User> provideUserList() {
        return new ArrayList<>();
    }
}