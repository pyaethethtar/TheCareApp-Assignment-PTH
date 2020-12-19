package com.example.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.GeneralQuestionVO
import com.example.shared.data.vos.PatientVO

@Dao
interface GeneralQuestionDao {

    @Query("SELECT * FROM generalQuestions")
    fun getAllGeneralQuestions() : LiveData<List<GeneralQuestionVO>>

    @Query("DELETE FROM generalQuestions")
    fun deleteAllGeneralQuestions()

    @Delete
    fun deleteGeneralQuestion(generalQuestion: GeneralQuestionVO)

    @Insert
    fun addAllGeneralQuestions(questions : List<GeneralQuestionVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewQuestion(question : GeneralQuestionVO)
}