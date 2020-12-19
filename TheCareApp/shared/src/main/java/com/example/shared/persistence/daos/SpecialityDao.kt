package com.example.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.PatientVO
import com.example.shared.data.vos.SpecialityVO

@Dao
interface SpecialityDao {

    @Query("SELECT * FROM specialities")
    fun getAllSpecialities() : LiveData<List<SpecialityVO>>

    @Query("SELECT * FROM specialities WHERE id=:id")
    fun getSpecialityById(id : String) : LiveData<SpecialityVO>

    @Query("SELECT * FROM specialities WHERE speciality=:speciality")
    fun getSpeciality(speciality : String) : LiveData<SpecialityVO>

    @Query("DELETE FROM specialities")
    fun deleteAllSpecialities()

    @Delete
    fun deleteSpeciality(speciality: SpecialityVO)

    @Insert
    fun addAllSpecialities(specialities : List<SpecialityVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSpeciality(speciality : SpecialityVO)
}