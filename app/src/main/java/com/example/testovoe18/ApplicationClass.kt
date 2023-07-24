package com.example.testovoe18

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "27d0e8a9-7747-42f8-b987-91d0f76ae0f1"

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}