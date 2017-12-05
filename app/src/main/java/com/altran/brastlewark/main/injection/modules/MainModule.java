package com.altran.brastlewark.main.injection.modules;

import com.altran.brastlewark.common.injection.scopes.PerActivity;
import com.altran.brastlewark.main.view.presenter.MainPresenter;
import com.altran.brastlewark.user.domain.usecase.GetUser;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Athmos on 11/11/2017.
 */

@Module
public class MainModule {

    @Provides
    @PerActivity
    MainPresenter providesSplashPresenter(GetUser getUser) {
        return new MainPresenter(getUser);
    }
}
