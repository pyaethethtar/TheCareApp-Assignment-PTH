package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName
import java.util.*

@IgnoreExtraProperties
@Entity(tableName = "doctors")
data class DoctorVO(

    @PrimaryKey()
    @get:PropertyName("id") @set:PropertyName("id")
    var doctorId : String = "",

    @get:PropertyName("name") @set:PropertyName("name")
    var doctorName : String = "",

    @get:PropertyName("email") @set:PropertyName("email")
    var doctorEmail : String ?= "",

    @get:PropertyName("password") @set:PropertyName("password")
    var doctorPassword : String ?= "",

    @get:PropertyName("phone") @set:PropertyName("phone")
    var doctorPhone : String ?= "",

    @get:PropertyName("image") @set:PropertyName("image")
    var doctorProfileImage : String ?= "",

    var speciality : String = "",

    @get:PropertyName("dob") @set:PropertyName("dob")
    var doctorDob : String ?= "",

    @get:PropertyName("gender") @set:PropertyName("gender")
    var doctorGender : String ?= "",

    @get:PropertyName("experience_year") @set:PropertyName("experience_year")
    var experienceYear : Int ?= 0,

    @get:PropertyName("certificates") @set:PropertyName("certificates")
    var certificates : String ?= "",

    @get:PropertyName("brief") @set:PropertyName("brief")
    var doctorBrief : String ?= "",

    @get:PropertyName("address") @set:PropertyName("address")
    var doctorAddress: String ?= "",

    @get:PropertyName("device_token") @set:PropertyName("device_token")
    var doctorDeviceToken: String ?= ""
)