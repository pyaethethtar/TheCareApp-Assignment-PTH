package com.example.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.PatientVO
import org.jetbrains.annotations.NotNull

@Dao
interface PatientDao {

    @Query("SELECT * FROM patients")
    fun getAllPatients() : LiveData<List<PatientVO>>

    @Query("SELECT * FROM patients WHERE patientId=:id")
    fun getPatientById(id : String) : LiveData<PatientVO>

    @Query("DELETE FROM patients")
    fun deleteAllPatients()

    @Delete
    fun deletePatient(patient: PatientVO)

    @Insert
    fun addAllPatients(patients : List<PatientVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPatient(patient: PatientVO)

//    @NotNull
//    @Query("SELECT recentDoctorIds FROM patients WHERE patientId=:patientId")
//    fun getRecentDoctors(patientId: String) : LiveData<ArrayList<String>>




}