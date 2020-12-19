package com.example.shared.data.models

import android.content.Context
import com.example.shared.persistence.db.TheCareDB

abstract class BaseModel {

    protected lateinit var mTheDB : TheCareDB

    fun initDatabase(context: Context){
        mTheDB = TheCareDB.getDBInstance(context)
    }
}