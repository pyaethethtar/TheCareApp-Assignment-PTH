package com.example.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.DoctorVO

@Dao
interface DoctorDao {

    @Query("SELECT * FROM doctors")
    fun getAllDoctors() : LiveData<List<DoctorVO>>

    @Query("SELECT * FROM doctors WHERE doctorId=:id")
    fun getDoctorById(id : String) : LiveData<DoctorVO>

    @Query("DELETE FROM doctors")
    fun deleteAllDoctors()

    @Delete
    fun deleteDoctor(doctor: DoctorVO)

    @Insert
    fun addAllDoctors(doctors : List<DoctorVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDoctor(doctor : DoctorVO)
}