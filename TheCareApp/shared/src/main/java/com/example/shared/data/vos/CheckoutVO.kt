package com.example.shared.data.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shared.persistence.typeconverters.MedicationVOTypeConverter
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
@Entity(tableName = "checkouts")
@TypeConverters(MedicationVOTypeConverter::class)
data class CheckoutVO (

    @PrimaryKey
    @get:PropertyName("id") @set:PropertyName("id")
    var checkoutId : String = "",

    @Embedded
    @get:PropertyName("patient_info") @set:PropertyName("patient_info")
    var patientInfo : PatientVO = PatientVO(),

    @Embedded
    @get:PropertyName("delivery_info") @set:PropertyName("delivery_info")
    var deliveryInfo : DeliveryVO ?= DeliveryVO(),

    var prescriptions : ArrayList<MedicationVO> ?= arrayListOf(),

    @get:PropertyName("total_amount") @set:PropertyName("total_amount")
    var totalAmount : Int = 0
    )