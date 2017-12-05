package com.altran.brastlewark.main.injection.components;

import com.altran.brastlewark.common.injection.scopes.PerActivity;
import com.altran.brastlewark.main.injection.modules.MainModule;
import com.altran.brastlewark.main.view.activity.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Athmos on 11/11/2017.
 */

@PerActivity
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
