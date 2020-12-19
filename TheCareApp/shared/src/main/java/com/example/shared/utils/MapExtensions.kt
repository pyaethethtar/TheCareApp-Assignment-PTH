package com.example.shared.utils

import com.example.shared.data.vos.*
import com.example.shared.network.CloudFirestoreDataAgentImpl

fun MutableMap<String, Any>.toDoctorVO() : DoctorVO{
    val doctorVO = DoctorVO()
    this.get("id")?.let {
        doctorVO.doctorId = it as String
    }
    this.get("name")?.let {
        doctorVO.doctorName = it as String
    }
    this.get("email")?.let {
        doctorVO.doctorEmail = it as String
    }
    this.get("password")?.let {
        doctorVO.doctorPassword = it as String
    }
    this.get("phone")?.let {
        doctorVO.doctorPhone = it as String
    }
    this.get("image")?.let {
        doctorVO.doctorProfileImage = it as String
    }
    this.get("speciality")?.let {
        doctorVO.speciality = it as String
    }
    this.get("dob")?.let {
        doctorVO.doctorDob = it as String
    }
    this.get("gender")?.let {
        doctorVO.doctorGender = it as String
    }
    this.get("experience_year")?.let {
        doctorVO.experienceYear = (it as Long).toInt()
    }
    this.get("certificates")?.let {
        doctorVO.certificates = it as String
    }
    this.get("brief")?.let {
        doctorVO.doctorBrief = it as String
    }
    this.get("address")?.let {
        doctorVO.doctorAddress = it as String
    }
    this.get("device_token")?.let {
        doctorVO.doctorDeviceToken = it as String
    }
    return doctorVO
}

fun MutableMap<String, Any>.toPatientVO() : PatientVO{
    val patientVO = PatientVO()
    this.get("id")?.let {
        patientVO.patientId = it as String
    }
    this.get("name")?.let {
        patientVO.patientName = it as String
    }
    this.get("email")?.let {
        patientVO.patientEmail = it as String
    }
    this.get("password")?.let {
        patientVO.patientPassword = it as String
    }
    this.get("phone")?.let {
        patientVO.patientPhone = it as String
    }
    this.get("image")?.let {
        patientVO.patientProfileImage = it as String
    }
    this.get("recent_doctor_ids")?.let {
        patientVO.recentDoctorIds = it as ArrayList<String>
    }
    this.get("address")?.let {
        patientVO.patientAddress = it as String
    }
    this.get("device_token")?.let {
        patientVO.patientDeviceToken = it as String
    }
    return patientVO
}

fun MutableMap<String, Any>.toConsultationVO() : ConsultationVO{
    val consultationVO = ConsultationVO()
    consultationVO.consultationId = this.get("id") as String
    consultationVO.doctorInfo = (this["doctor_info"] as MutableMap<String, Any>).toDoctorVO()
    consultationVO.patientInfo = (this["patient_info"]  as MutableMap<String, Any>).toPatientVO()
    consultationVO.consultationDate = this.get("consultation_date") as String
    consultationVO.status = this.get("status") as String

    return consultationVO
}

fun MutableMap<String, Any>.toGeneralQuestionVO() : GeneralQuestionVO{
    val generalQuestionVO = GeneralQuestionVO()
    generalQuestionVO.gid = this.get("id") as String
    generalQuestionVO.generalQuestion = this.get("question") as String
    generalQuestionVO.category = this.get("category") as String
    return generalQuestionVO
}

fun MutableMap<String, Any>.toConsultationRequestVO() : ConsultationRequestVO{
    val consultationRequestVO = ConsultationRequestVO()
    consultationRequestVO.requestId = this.get("id") as String
    consultationRequestVO.patientInfo = (this.get("patient_info") as MutableMap<String, Any>).toPatientVO()
    consultationRequestVO.speciality = this["speciality"] as String
    consultationRequestVO.status = this["status"] as String

    return consultationRequestVO
}

fun MutableMap<String, Any>.toCaseSummaryVO() : CaseSummaryVO{
    val caseSummaryVO = CaseSummaryVO()
    caseSummaryVO.question = this.get("question") as String
    caseSummaryVO.answer = this.get("answer") as String
    return caseSummaryVO
}


fun MutableMap<String, Any>.toDeliveryVO() : DeliveryVO{
    val deliveryVO = DeliveryVO()
    this.get("address")?.let {
        deliveryVO.address = it as String
    }
    this.get("fee")?.let {
        deliveryVO.fee = (it as Long).toInt()
    }
    return deliveryVO
}


fun MutableMap<String, Any>.toChatVO() : ChatVO{
    val chatVO = ChatVO()
    this.get("doctor")?.let {
        chatVO.doctor = it as String
    }
    this.get("patient")?.let {
        chatVO.patient = it as String
    }
    this.get("doctor_image")?.let {
        chatVO.doctorImage = it as String
    }
    this.get("patient_image")?.let {
        chatVO.patientImage = it as String
    }
    return chatVO
}

fun MutableMap<String, Any>.toMedicationVO() : MedicationVO{
    val medicationVO = MedicationVO()
    this.get("medicine")?.let {
        medicationVO.medicine = it as String
    }
    this.get("price")?.let {
        medicationVO.price = (it as Long).toInt()
    }
    this.get("count")?.let {
        medicationVO.count = (it as Long).toInt()
    }
    this.get("taking_days")?.let {
        medicationVO.takingDays = (it as Long).toInt()
    }
    this.get("taking_times")?.let {
        medicationVO.takingTimes = it as String
    }

    return medicationVO
}


fun MutableMap<String, Any>.toSpecialQuestionVO() : SpecialQuestionVO{
    val specialQuestionVO = SpecialQuestionVO()
    specialQuestionVO.specialQuestion = this.get("question") as String
    this.get("choices")?.let {
        specialQuestionVO.choices = it as ArrayList<String>
    }
    return specialQuestionVO
}


fun MutableMap<String, Any>.toSpecialityVO() : SpecialityVO{
    val specialityVO = SpecialityVO()
    specialityVO.id = this.get("id") as String
    specialityVO.image = this["image"] as String
    specialityVO.speciality = this["speciality"] as String
    specialityVO.doctorQuestions = this["doctor_questions"] as ArrayList<String>
    return specialityVO
}

fun MutableMap<String, Any>.toCheckoutVO() : CheckoutVO{
    val checkoutVO = CheckoutVO()
    checkoutVO.checkoutId = this.get("id") as String
    checkoutVO.patientInfo = (this.get("patient_info") as MutableMap<String, Any>).toPatientVO()
    checkoutVO.deliveryInfo = (this.get("delivery_info") as MutableMap<String, Any>).toDeliveryVO()
    checkoutVO.totalAmount = (this.get("total_amount") as Long).toInt()
    return checkoutVO
}