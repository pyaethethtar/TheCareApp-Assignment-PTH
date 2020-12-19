package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shared.persistence.typeconverters.CaseSummaryVOTypeConverter
import com.example.shared.persistence.typeconverters.StringTypeConverter
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
@Entity(tableName = "patients")
@TypeConverters(StringTypeConverter::class, CaseSummaryVOTypeConverter::class)
data class PatientVO (

    @PrimaryKey
    @get:PropertyName("id") @set:PropertyName("id")
    var patientId : String = "",

    @get:PropertyName("name") @set:PropertyName("name")
    var patientName : String = "",

    @get:PropertyName("email") @set:PropertyName("email")
    var patientEmail : String = "",

    @get:PropertyName("password") @set:PropertyName("password")
    var patientPassword : String ?= "",

    @get:PropertyName("phone") @set:PropertyName("phone")
    var patientPhone : String ?= "",

    @get:PropertyName("image") @set:PropertyName("image")
    var patientProfileImage : String ?= "",

    @get:PropertyName("recent_doctor_ids") @set:PropertyName("recent_doctor_ids")
    var recentDoctorIds : ArrayList<String> ?= arrayListOf(),

    @get:PropertyName("case_summary") @set:PropertyName("case_summary")
    var patientCaseSummary : ArrayList<CaseSummaryVO> ?= arrayListOf(),

    @get:PropertyName("address") @set:PropertyName("address")
    var patientAddress : String ?= "",

    @get:PropertyName("device_token") @set:PropertyName("device_token")
    var patientDeviceToken : String ?= ""

)