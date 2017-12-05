package com.altran.brastlewark;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Athmos on 11/11/2017.
 */

public class BrastlewarkActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks {

    private Set<String> startedActivities = new HashSet<>();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        String name = activity.getClass().getSimpleName();
        startedActivities.add(name);
        synchronized (this) {
            BrastlewarkBaseApplication.isInForeground = true;
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        String name = activity.getClass().getSimpleName();
        startedActivities.remove(name);
        synchronized (this) {
            BrastlewarkBaseApplication.isInForeground = !startedActivities.isEmpty();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
