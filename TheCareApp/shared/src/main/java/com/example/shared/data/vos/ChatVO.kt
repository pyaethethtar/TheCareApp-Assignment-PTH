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
    var patientImage : String ?= "",
    @get:PropertyName("sending_date") @set:PropertyName("sending_date")
    var sendingDate : String ?= "",
    @get:PropertyName("sending_time") @set:PropertyName("sending_time")
    var sendingTime : String ?= ""

)