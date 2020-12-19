package com.example.shared.data.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shared.persistence.typeconverters.CaseSummaryVOTypeConverter
import com.example.shared.persistence.typeconverters.ChatVOTypeConverter
import com.example.shared.persistence.typeconverters.MedicationVOTypeConverter
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
@Entity(tableName = "consultations")
@TypeConverters(CaseSummaryVOTypeConverter::class, ChatVOTypeConverter::class, MedicationVOTypeConverter::class)
data class ConsultationVO (

    @PrimaryKey
    @get:PropertyName("id") @set:PropertyName("id")
    var consultationId : String = "",

    @Embedded
    @get:PropertyName("doctor_info") @set:PropertyName("doctor_info")
    var doctorInfo : DoctorVO = DoctorVO(),

    @Embedded
    @get:PropertyName("patient_info") @set:PropertyName("patient_info")
    var patientInfo : PatientVO = PatientVO(),

    @get:PropertyName("case_summary") @set:PropertyName("case_summary")
    var caseSummary : ArrayList<CaseSummaryVO> = arrayListOf(),

    var chats : ArrayList<ChatVO> ?= arrayListOf(),

    var prescriptions : ArrayList<MedicationVO> ?= arrayListOf(),

    @get:PropertyName("consultation_date") @set:PropertyName("consultation_date")
    var consultationDate  :String = "",

    var status : String = ""
)