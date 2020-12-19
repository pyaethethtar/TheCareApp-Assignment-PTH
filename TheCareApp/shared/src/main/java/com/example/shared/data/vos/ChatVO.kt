package com.example.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
data class ChatVO (

    var doctor : String ?= "",
    var patient : String ?= "",
    @get:PropertyName("doctor_image") @set:PropertyName("doctor_image")
    var doctorImage : String ?= "",
    @get:PropertyName("patient_image") @set:PropertyName("patient_image")
    var patientImage : String ?= ""
)