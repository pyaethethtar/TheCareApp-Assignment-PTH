package com.example.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class DeliveryVO (

    var address: String ?= "",
    var fee : Int ?= 0
)