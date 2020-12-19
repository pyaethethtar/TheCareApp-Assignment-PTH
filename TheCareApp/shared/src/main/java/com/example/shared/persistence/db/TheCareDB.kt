package com.example.shared.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shared.data.vos.*
import com.example.shared.persistence.daos.*

@Database(entities = [SpecialityVO::class, DoctorVO::class, PatientVO::class,
    GeneralQuestionVO::class, ConsultationVO::class, ConsultationRequestVO::class,
    CheckoutVO::class], version = 3, exportSchema = false)
abstract class TheCareDB : RoomDatabase() {

    companion object{
        val DB_NAME = "The_Care_DB"
        var dbInstance : TheCareDB ?= null

        fun getDBInstance(context: Context) : TheCareDB{
            when(dbInstance){
                null -> {
                    dbInstance = Room.databaseBuilder(context, TheCareDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            val i = dbInstance
            return i!!
        }
    }

    abstract fun specialityDao() : SpecialityDao
    abstract fun doctorDao() : DoctorDao
    abstract fun patientDao() : PatientDao
    abstract fun generalQuestionDao() : GeneralQuestionDao
    abstract fun consultationDao() : ConsultationDao
    abstract fun consultationRequestDao() : ConsultationRequestDao
    abstract fun checkoutDao() : CheckoutDao
}