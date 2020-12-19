package com.example.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
data class MedicationVO (

    var medicine : String = "",
    var price : Int ?= 0,
    var count : Int ?= 0,
    @get:PropertyName("taking_days") @set:PropertyName("taking_days")
    var takingDays : Int ?= 0,
    @get:PropertyName("taking_times") @set:PropertyName("taking_times")
    var takingTimes : String ?= ""
)