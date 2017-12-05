package com.altran.brastlewark.common.injection.components;

import com.altran.brastlewark.common.injection.modules.AppModule;
import com.altran.brastlewark.common.injection.modules.DataModule;
import com.altran.brastlewark.main.injection.components.MainComponent;
import com.altran.brastlewark.main.injection.modules.MainModule;
import com.altran.brastlewark.user.injection.module.UserModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Athmos on 30/10/2017.
 */

@Singleton
@Component(modules = {
        AppModule.class, DataModule.class, UserModule.class
})
public interface ApplicationComponent {

    MainComponent getMainComponent(MainModule mainModule);
}
