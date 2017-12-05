package com.altran.brastlewark;

import android.app.Application;

import com.altran.brastlewark.common.injection.components.ApplicationComponent;

/**
 * Created by Athmos on 11/11/2017.
 */

public abstract class BrastlewarkBaseApplication extends Application {

    protected ApplicationComponent applicationComponent;

    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new BrastlewarkActivityLifecycleCallback();
    public static boolean isInForeground;

    public static BrastlewarkBaseApplication get(Application application) {
        return ((BrastlewarkBaseApplication) application);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
