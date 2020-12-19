package com.example.thecarefordoctor

import android.app.Application
import com.example.shared.data.models.TheCareModelImpl

class TheCareAppForDoctor  : Application(){

    override fun onCreate() {
        super.onCreate()
        TheCareModelImpl.initDatabase(applicationContext)
    }
}