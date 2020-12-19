package com.example.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.ChatVO
import com.example.shared.data.vos.ConsultationVO
import org.jetbrains.annotations.NotNull

@Dao
interface ConsultationDao {

    @Query("SELECT * FROM consultations")
    fun getAllConsultations() : LiveData<List<ConsultationVO>>

    @Query("SELECT * FROM consultations WHERE consultationId=:id")
    fun getConsultationById(id : String) : LiveData<ConsultationVO>


    @Query("DELETE FROM consultations")
    fun deleteAllConsultations()

    @Delete
    fun deleteConsultation(consultation: ConsultationVO)

    @Insert
    fun addAllConsultations(consultations : List<ConsultationVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewConsultation(consultation: ConsultationVO)


//    @NotNull
//    @Query("SELECT chats FROM consultations WHERE consultationId=:consultationId")
//    fun getChatMessages(consultationId: String) : LiveData<List<ChatVO>>
//
//    @Query("UPDATE consultations SET chats=:chats WHERE consultationId=:consultationId")
//    fun updateChatMessages(consultationId: String, chats: List<ChatVO>)


}