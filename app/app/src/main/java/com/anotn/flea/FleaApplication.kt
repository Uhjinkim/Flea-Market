package com.anotn.flea

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FleaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

}