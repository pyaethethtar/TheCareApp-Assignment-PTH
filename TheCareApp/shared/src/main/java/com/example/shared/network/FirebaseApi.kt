package com.example.shared.network

import android.graphics.Bitmap
import com.example.shared.data.vos.*

interface FirebaseApi {

    fun addNewDoctor(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addNewPatient(patientVO: PatientVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getDoctorInfo(doctorId: String, onSuccess: (DoctorVO) -> Unit, onFailure: (String) -> Unit)
    fun getPatientInfo(patientId: String, onSuccess: (PatientVO) -> Unit, onFailure: (String) -> Unit)
    fun getSpecialityList(onSuccess:(List<SpecialityVO>)->Unit, onFailure:(String)->Unit)
    fun getSpecialityById(speciality: String, onSuccess: (SpecialityVO) -> Unit, onFailure: (String) -> Unit)
    fun getGeneralQuestions(onSuccess: (List<GeneralQuestionVO>) -> Unit,onFailure: (String) -> Unit)
    fun getNewConsultationRequest(onSuccess: (List<ConsultationRequestVO>) -> Unit,
                                  onFailure: (String) -> Unit)
    fun getChatMessages(consultationId:String, onSuccess: (List<ChatVO>) -> Unit, onFailure: (String) -> Unit)
    fun getConsultationList(onSuccess: (List<ConsultationVO>) -> Unit,
                            onFailure: (String) -> Unit)
    fun getCheckoutInfo(checkoutId: String, onSuccess: (CheckoutVO) -> Unit, onFailure: (String) -> Unit)
    fun getDoctorListByIds(ids: ArrayList<String>, onSuccess: (List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)


    fun addConsultationRequest(consultationRequestVO: ConsultationRequestVO,
                               onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addChatTextMessage(consultationId:String, sender:String, message : String,
                           onSuccess:()->Unit, onFailure: (String) -> Unit)
    fun addChatImageMessage(consultationId:String, sender:String, image: Bitmap,
                            onSuccess:()->Unit, onFailure: (String) -> Unit)
    fun addCheckout(checkoutVO: CheckoutVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addNewConsultation(consultationVO: ConsultationVO,
                           onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addRecentlyConsultedDoctor(doctorId: String, patientId: String,
                                   onSuccess: () -> Unit, onFailure: (String) -> Unit)

}