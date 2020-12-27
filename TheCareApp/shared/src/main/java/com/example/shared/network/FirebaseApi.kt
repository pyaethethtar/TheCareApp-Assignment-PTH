package com.example.shared.network

import android.graphics.Bitmap
import com.example.shared.data.vos.*

interface FirebaseApi {

    fun addNewDoctor(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addNewPatient(patientVO: PatientVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun updateDoctorInfo(doctorVO: DoctorVO, image: Bitmap, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun updatePatientInfo(patientVO: PatientVO, image: Bitmap, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getDoctorInfo(doctorId: String, onSuccess: (DoctorVO) -> Unit, onFailure: (String) -> Unit)
    fun getPatientInfo(patientId: String, onSuccess: (PatientVO) -> Unit, onFailure: (String) -> Unit)
    fun getSpecialityList(onSuccess:(List<SpecialityVO>)->Unit, onFailure:(String)->Unit)
    fun getSpecialityById(speciality: String, onSuccess: (SpecialityVO) -> Unit, onFailure: (String) -> Unit)
    fun getGeneralQuestions(onSuccess: (List<GeneralQuestionVO>) -> Unit,onFailure: (String) -> Unit)
    fun getNewConsultationRequest(onSuccess: (List<ConsultationRequestVO>) -> Unit,
                                  onFailure: (String) -> Unit)
    fun getNewConsultation(onSuccess: (List<ConsultationVO>) -> Unit, onFailure: (String) -> Unit)
    fun getChatMessages(consultationId:String, onSuccess: (ArrayList<ChatVO>) -> Unit, onFailure: (String) -> Unit)
    fun getMedicationList(consultationId: String, onSuccess: (ArrayList<MedicationVO>)->Unit, onFailure: (String) -> Unit)
    fun getConsultationList(onSuccess: (List<ConsultationVO>) -> Unit,
                            onFailure: (String) -> Unit)
    fun getConsultationById(consultationId: String, onSuccess:(ConsultationVO)->Unit, onFailure: (String) -> Unit)
    fun getCheckoutInfo(checkoutId: String, onSuccess: (CheckoutVO) -> Unit, onFailure: (String) -> Unit)
    fun getDoctorListByIds(ids: ArrayList<String>, onSuccess: (List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)


    fun addConsultationRequest(consultationRequestVO: ConsultationRequestVO,
                               onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun updateStatusConsultationRequest(requestId: String, status: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addChatTextMessage(consultationId:String, sender:String, message : String,
                           onSuccess:()->Unit, onFailure: (String) -> Unit)
    fun addChatImageMessage(consultationId:String, sender:String, image: Bitmap,
                            onSuccess:()->Unit, onFailure: (String) -> Unit)
    fun addCheckout(checkoutVO: CheckoutVO, onSuccess: (id: String) -> Unit, onFailure: (String) -> Unit)
    fun addAddressToCheckout(checkoutId: String, deliveryVO: DeliveryVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addNewConsultation(consultationVO: ConsultationVO,
                           onSuccess: (consultationId:String) -> Unit, onFailure: (String) -> Unit)
    fun addMedicationListToConsultation(consultationId: String, medications: ArrayList<MedicationVO>)
    fun addNoteToConsultation(consultationId: String, note: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun finishConsultation(consultationId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addRecentlyConsultedDoctor(doctorId: String, patientId: String,
                                   onSuccess: () -> Unit, onFailure: (String) -> Unit)

}