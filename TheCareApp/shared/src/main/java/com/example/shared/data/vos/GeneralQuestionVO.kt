package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
@Entity(tableName = "generalQuestions")
data class GeneralQuestionVO (
    @PrimaryKey
    @get:PropertyName("id") @set:PropertyName("id")
    var gid: String = "",

    @get:PropertyName("question") @set:PropertyName("question")
    var generalQuestion : String = "",
    var category : String = ""
)