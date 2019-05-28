package com.ticket.arms.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ticket.arms.base.delegate.IFragment;
import com.ticket.arms.di.component.AppComponent;
import com.ticket.arms.integration.cache.Cache;
import com.ticket.arms.integration.cache.CacheType;
import com.ticket.arms.integration.lifecycle.FragmentLifecycleable;
import com.ticket.arms.mvp.IPresenter;
import com.ticket.arms.utils.ArmsUtils;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * @author :dadade
 * date   :2019-05-28 10:58
 * desc   :
 */
public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IFragment, FragmentLifecycleable {

    private Cache mCache;
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject=BehaviorSubject.create();
    private Context mContext;
    @Inject
    @Nullable
    protected P mPresenter;

    @NotNull
    @Override
    public  synchronized Cache<String, Object> provideCache() {
        if (mCache==null){
            mCache = ArmsUtils.obtainAppComponentFromContext(getActivity()).cacheFactory().build(CacheType.FRAGMENT_CACHE);
        }
        return mCache;
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater,container,savedInstanceState);
    }

    @Override
    public @NotNull Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter=null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.onDestroy();
        }
        mPresenter=null;
    }
}
