package com.example.shared.data.models

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.example.shared.data.vos.*
import com.example.shared.network.FirebaseApi

interface TheCareModel {

    var mFirebaseApi : FirebaseApi
    var consultationRequestVO : ConsultationRequestVO
    var mPatientId: String

    var mSpeciality : String
    var mConsultationId  :String
    var medicationList : ArrayList<MedicationVO>

    fun getDataFromApiAndSaveToPatientDB(patientId: String, onSuccess:()->Unit, onFailure:(String)->Unit)
    fun getDataFromApiAndSaveToDoctorDB(doctorId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addDoctorInfoToDB(doctorId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addPatientInfoToDB(patientId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addNewConsultationRequestToDB(speciality: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addConsultationListToDoctorDB(doctorId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addConsultationListToPatientDB(patientId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun getConsultationByIdAndSaveToDB(consultationId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addCheckoutInfoToDB(checkoutId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addRecentDoctorsToDB(ids: ArrayList<String>, onSuccess:()->Unit, onFailure: (String) -> Unit)
    fun getChatMessagesAndSaveToDB(consultationVO: ConsultationVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun getMedicationListAndSaveToDB(consultationVO: ConsultationVO, onSuccess: ()->Unit, onFailure: (String) -> Unit)
    //fun getNewConsultationAndSaveToDB(onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun addNewDoctor(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addNewPatient(patientVO: PatientVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun updateDoctorInfo(doctorVO: DoctorVO, image: Bitmap, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun updatePatientInfo(patientVO: PatientVO, image: Bitmap, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addConsultationRequest(consultationRequestVO: ConsultationRequestVO,
                               onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun updateStatusConsultationRequest(requestId: String, status: String, onSuccess: () -> Unit,
                                        onFailure: (String) -> Unit)
    fun addNewConsultation(consultationVO: ConsultationVO, onSuccess: (consultationId:String) -> Unit, onFailure: (String) -> Unit)
    fun addMedicationListToConsultation(consultationId: String, medications: ArrayList<MedicationVO>)
    fun addNoteToConsultation(consultationId: String, note: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun finishConsultation(consultationId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addChatTextMessage(consultationId:String, sender:String, message : String, onSuccess:()->Unit,
                           onFailure: (String) -> Unit)
    fun addChatImageMessage(consultationId:String, sender:String, image: Bitmap, onSuccess:()->Unit,
                            onFailure: (String) -> Unit)
    fun addCheckout(checkoutVO: CheckoutVO, onSuccess: (id: String) -> Unit, onFailure: (String) -> Unit)
    fun addAddressToCheckout(checkoutId: String, deliveryVO: DeliveryVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addRecentlyConsultedDoctor(doctorId: String, patientId: String,
                                   onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getConsultationRequests(onFailure: (String) -> Unit) : LiveData<List<ConsultationRequestVO>>
    fun getConsultationRequestById(requestId: String, onFailure: (String) -> Unit) : LiveData<ConsultationRequestVO>
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