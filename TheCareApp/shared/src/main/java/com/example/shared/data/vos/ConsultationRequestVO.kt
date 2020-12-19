package com.example.shared.data.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shared.persistence.typeconverters.CaseSummaryVOTypeConverter
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
@Entity(tableName = "consultationRequests")
@TypeConverters(CaseSummaryVOTypeConverter::class)
data class ConsultationRequestVO (

    @PrimaryKey
    @get:PropertyName("id") @set:PropertyName("id")
    var requestId : String = "",

    var speciality : String = "",
    var status : String = "",

    @Embedded
    @get:PropertyName("patient_info") @set:PropertyName("patient_info")
    var patientInfo : PatientVO = PatientVO(),

    @get:PropertyName("case_summary") @set:PropertyName("case_summary")
    var caseSummary : ArrayList<CaseSummaryVO> = arrayListOf()
)