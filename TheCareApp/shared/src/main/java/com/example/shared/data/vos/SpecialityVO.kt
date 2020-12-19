package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shared.persistence.typeconverters.MedicationVOTypeConverter
import com.example.shared.persistence.typeconverters.SpecialQuestionVOTypeConverter
import com.example.shared.persistence.typeconverters.StringTypeConverter
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
@Entity(tableName = "specialities")
@TypeConverters(SpecialQuestionVOTypeConverter::class, StringTypeConverter::class, MedicationVOTypeConverter::class)
data class SpecialityVO (

    @PrimaryKey
    var id : String = "",
    var speciality : String = "",
    var image : String = "",
    @get:PropertyName("special_questions") @set:PropertyName("special_questions")
    var specialQuestions : ArrayList<SpecialQuestionVO> = arrayListOf(),
    @get:PropertyName("doctor_questions") @set:PropertyName("doctor_questions")
    var doctorQuestions : ArrayList<String> = arrayListOf(),
    var medication : ArrayList<MedicationVO> = arrayListOf()
)