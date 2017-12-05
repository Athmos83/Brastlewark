package com.altran.brastlewark.common.view;

import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

/**
 * Created by Athmos on 11/11/2017.
 */

public class BasePresenter<V extends BaseView>  {
    protected WeakReference<V> view;

    @UiThread
    public void attachView(V view) {
        this.view = new WeakReference(view);
    }

    @UiThread
    public V getView() {
        return view == null ? null : view.get();
    }

    @UiThread
    public boolean isViewAttached() {
        return view != null && view.get() != null;
    }
}
