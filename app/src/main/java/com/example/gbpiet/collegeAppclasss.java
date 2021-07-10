package com.example.gbpiet;

import android.app.Application;

import com.onesignal.OneSignal;

public class collegeAppclasss<Static> extends Application {

    private final String ONESIGNAL_APP_ID = "6df45d0b-9abc-4de0-8d5d-3727fb2ac784";

    @Override
    public void onCreate() {
        super.onCreate();
//        Enable verbose oneSignal logging to debug issues
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE,OneSignal.LOG_LEVEL.NONE);

//        OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}
