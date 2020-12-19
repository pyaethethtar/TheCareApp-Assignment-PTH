package com.example.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class CaseSummaryVO (
    var question : String = "",
    var answer : String = ""
)