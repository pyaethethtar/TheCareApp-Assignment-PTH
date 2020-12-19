package com.example.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.ConsultationRequestVO

@Dao
interface ConsultationRequestDao {

    @Query("SELECT * FROM consultationRequests")
    fun getAllConsultationRequests() : LiveData<List<ConsultationRequestVO>>

    @Query("SELECT * FROM consultationRequests WHERE requestId=:id")
    fun getConsultationRequestById(id : String) : LiveData<ConsultationRequestVO>


    @Query("DELETE FROM consultationRequests")
    fun deleteAllConsultationRequests()

    @Delete
    fun deleteConsultationRequest(consultationRequest: ConsultationRequestVO)

    @Insert
    fun addAllConsultationRequests(consultationRequests : List<ConsultationRequestVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewConsultationRequest(request : ConsultationRequestVO)
}