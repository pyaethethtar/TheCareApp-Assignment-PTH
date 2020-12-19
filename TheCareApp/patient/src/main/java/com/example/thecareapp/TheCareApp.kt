package com.example.thecareapp

import android.app.Application
import com.example.shared.data.models.TheCareModelImpl

class TheCareApp : Application() {

    override fun onCreate() {
        super.onCreate()
        TheCareModelImpl.initDatabase(applicationContext)
    }
}