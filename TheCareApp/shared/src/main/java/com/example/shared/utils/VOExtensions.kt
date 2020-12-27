package com.example.shared.utils

import com.example.shared.data.vos.*
import java.io.Serializable

fun DoctorVO.toDoctorMap() : HashMap<String, Any?>{
    val doctorMap = hashMapOf(
        "id" to this.doctorId,
        "name" to this.doctorName,
        "email" to this.doctorEmail,
        "password" to this.doctorPassword,
        "phone" to this.doctorPhone,
        "image" to this.doctorProfileImage,
        "speciality" to this.speciality,
        "dob" to this.doctorDob,
        "gender" to this.doctorGender,
        "experience_year" to this.experienceYear?.toLong(),
        "certificates" to this.certificates,
        "brief" to this.doctorBrief,
        "address" to this.doctorAddress,
        "device_token" to this.doctorDeviceToken
    )
    return doctorMap
}

fun PatientVO.toPatientMap() : HashMap<String, Serializable?>{
    val patientMap = hashMapOf(
        "id" to this.patientId,
        "name" to this.patientName,
        "email" to this.patientEmail,
        "password" to this.patientPassword,
        "phone" to this.patientPhone,
        "image" to this.patientProfileImage,
        "recent_doctor_ids" to this.recentDoctorIds,
        "address" to this.patientAddress,
        "device_token" to this.patientDeviceToken
    )
    return patientMap
}

fun ConsultationRequestVO.toConsultationRequestMap() : HashMap<String, Serializable>{
    val consultationRequestMap = hashMapOf(
        "id" to this.requestId,
        "speciality" to this.speciality,
        "status" to this.status,
        "patient_info" to this.patientInfo.toPatientMap()
    )
    return consultationRequestMap
}

fun ConsultationVO.toConsultationMap() : HashMap<String, Serializable?>{
    val consultationMap = hashMapOf(
        "id" to this.consultationId,
        "doctor_info" to this.doctorInfo.toDoctorMap(),
        "patient_info" to this.patientInfo.toPatientMap(),
        "consultation_date" to this.consultationDate,
        "status" to this.status,
        "consultation_note" to this.consultationNote
    )
    return consultationMap
}

fun CheckoutVO.toCheckoutMap() : HashMap<String, Serializable?>{
    val checkoutMap = hashMapOf(
        "id" to this.checkoutId,
        "patient_info" to this.patientInfo.toPatientMap(),
        "delivery_info" to this.deliveryInfo?.toDeliveryMap(),
        "total_amount" to this.totalAmount.toLong()
    )
    return checkoutMap
}

fun DeliveryVO.toDeliveryMap() : HashMap<String, Any?>{
    val deliveryMap = hashMapOf(
        "address" to this.address,
        "fee" to this.fee?.toLong()
    )
    return deliveryMap
}


fun MedicationVO.toMedicationMap() : HashMap<String, Any?>{
    val medicationMap = hashMapOf(
        "medicine" to this.medicine,
        "count" to this.count?.toLong(),
        "price" to this.price?.toLong(),
        "taking_days" to this.takingDays?.toLong(),
        "taking_times" to this.takingTimes,
        "before_after" to this.beforeAfter,
        "medication_note" to this.medicationNote
    )
    return  medicationMap
}

fun CaseSummaryVO.toCaseSummaryMap() : HashMap<String, String>{
    val caseSummaryMap = hashMapOf(
        "question" to this.question,
        "answer" to this.answer
    )
    return caseSummaryMap
}

fun ChatVO.toChatMap() :  HashMap<String, String?>{
    val chatMap = hashMapOf(
        "doctor" to this.doctor,
        "patient" to this.patient,
        "doctor_image" to this.doctorImage,
        "patient_image" to this.patientImage,
        "sending_date" to this.sendingDate,
        "sending_time" to this.sendingTime
    )
    return chatMap
}