package com.example.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
data class SpecialQuestionVO (

    @get:PropertyName("question") @set:PropertyName("question")
    var specialQuestion : String = "",
    var choices : ArrayList<String> ?= arrayListOf()
)