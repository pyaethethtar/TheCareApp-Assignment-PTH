package com.example.shared.data.models

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.example.shared.data.vos.*
import com.example.shared.network.FirebaseApi

interface TheCareModel {

    var mFirebaseApi : FirebaseApi

    fun getDataFromApiAndSaveToPatientDB(patientId: String, onSuccess:()->Unit, onFailure:(String)->Unit)
    fun getDataFromApiAndSaveToDoctorDB(doctorId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addDoctorInfoToDB(doctorId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addPatientInfoToDB(patientId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addNewConsultationRequestToDB(speciality: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addConsultationListToDoctorDB(doctorId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addConsultationListToPatientDB(patientId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addChatMessageToDB(consultationId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addCheckoutInfoToDB(checkoutId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addRecentDoctorsToDB(ids: ArrayList<String>, onSuccess:()->Unit, onFailure: (String) -> Unit)

    fun addNewDoctor(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addNewPatient(patientVO: PatientVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addConsultationRequest(consultationRequestVO: ConsultationRequestVO,
                               onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addNewConsultation(consultationVO: ConsultationVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addChatTextMessage(consultationId:String, sender:String, message : String, onSuccess:()->Unit,
                           onFailure: (String) -> Unit)
    fun addChatImageMessage(consultationId:String, sender:String, image: Bitmap, onSuccess:()->Unit,
                            onFailure: (String) -> Unit)
    fun addCheckout(checkoutVO: CheckoutVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addRecentlyConsultedDoctor(doctorId: String, patientId: String,
                                   onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getConsultationRequests(onFailure: (String) -> Unit) : LiveData<List<ConsultationRequestVO>>
    fun getConsultationRequestById(requestId: String, onFailure: (String) -> Unit) : LiveData<ConsultationRequestVO>
    fun getChatMessages(consultationId:String, onFailure: (String) -> Unit) : LiveData<List<ChatVO>>
    fun getConsultationList(onFailure: (String) -> Unit) : LiveData<List<ConsultationVO>>
    fun getConsultationById(consultationId:String, onFailure: (String) -> Unit) : LiveData<ConsultationVO>
    fun getCheckoutInfo(checkoutId: String, onFailure: (String) -> Unit) : LiveData<CheckoutVO>
    fun getDoctorsList(onFailure: (String) -> Unit) : LiveData<List<DoctorVO>>
    fun getDoctorById(id: String, onFailure: (String) -> Unit) : LiveData<DoctorVO>
    fun getPatientById(id: String, onFailure: (String) -> Unit) : LiveData<PatientVO>
    fun getAllSpecialities(onFailure: (String) -> Unit) : LiveData<List<SpecialityVO>>
    fun getSpeciality(speciality: String, onFailure: (String) -> Unit) : LiveData<SpecialityVO>
    fun getRecentlyConsultedDoctorIds(patientId:String, onFailure: (String) -> Unit) : LiveData<ArrayList<String>>
    fun getRecentlyConsultedDoctorList(onFailure: (String) -> Unit) : LiveData<List<DoctorVO>>

}