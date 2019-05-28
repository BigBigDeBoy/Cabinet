package com.ticket.cabinet.di.component;

import com.ticket.arms.di.component.AppComponent;
import com.ticket.arms.di.scope.ActivityScope;
import com.ticket.cabinet.di.module.MainModule;
import com.ticket.cabinet.mvp.contract.MainContract;
import com.ticket.cabinet.mvp.ui.activity.MainActivity;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainComponent.Builder view(MainContract.View view);

        MainComponent.Builder appComponent(AppComponent appComponent);

        MainComponent build();
    }
}