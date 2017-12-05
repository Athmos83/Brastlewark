package com.altran.brastlewark;

import com.altran.brastlewark.common.injection.components.DaggerApplicationComponent;
import com.altran.brastlewark.common.injection.modules.AppModule;

/**
 * Created by Athmos on 11/11/2017.
 */

public class BrastlewarkApplication extends BrastlewarkBaseApplication {

    @Override
    public void onCreate() {

        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this)).build();
    }
}
