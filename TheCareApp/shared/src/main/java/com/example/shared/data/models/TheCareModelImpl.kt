package com.example.shared.data.models

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shared.data.vos.*
import com.example.shared.network.CloudFirestoreDataAgentImpl
import com.example.shared.network.FirebaseApi
import com.example.shared.utils.CONSULTATION_STATUS_END
import com.example.shared.utils.REQUEST_STATUS_REQUEST

object TheCareModelImpl : TheCareModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = CloudFirestoreDataAgentImpl
    override var consultationRequestVO: ConsultationRequestVO = ConsultationRequestVO()
    override var mPatientId: String = ""

    override var mSpeciality: String = ""
    override var mConsultationId: String = ""
    override var medicationList: ArrayList<MedicationVO> = arrayListOf()

    override fun getDataFromApiAndSaveToPatientDB(
        patientId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialityList(onSuccess = {
            mTheDB.specialityDao().deleteAllSpecialities()
            mTheDB.specialityDao().addAllSpecialities(it)
        }, onFailure = {
            onFailure(it)
        })

//        mFirebaseApi.getGeneralQuestions(onSuccess = {
//            mTheDB.generalQuestionDao().addAllGeneralQuestions(it)
//        }, onFailure = {
//            onFailure(it)
//        })

        mFirebaseApi.getPatientInfo(patientId, onSuccess = {
            mTheDB.patientDao().deleteAllPatients()
            mTheDB.patientDao().addPatient(it)
        }, onFailure = {
            onFailure(it)
        })

        //addConsultationListToPatientDB(patientId, onSuccess, onFailure)

    }

    override fun getDataFromApiAndSaveToDoctorDB(
        doctorId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {


        mFirebaseApi.getDoctorInfo(doctorId, onSuccess = {
            mTheDB.doctorDao().deleteAllDoctors()
            mTheDB.doctorDao().addDoctor(it)
            mSpeciality = it.speciality

            mFirebaseApi.getSpecialityById(mSpeciality, onSuccess = {
                mTheDB.specialityDao().deleteAllSpecialities()
                mTheDB.specialityDao().addSpeciality(it)
            }, onFailure = {
                onFailure(it)
            })

        }, onFailure = {
            onFailure(it)
        })


        //addConsultationListToDoctorDB(doctorId, onSuccess, onFailure)

    }

    override fun addDoctorInfoToDB(
        doctorId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getDoctorInfo(doctorId, onSuccess = {
            mTheDB.doctorDao().addDoctor(it)
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun addPatientInfoToDB(
        patientId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getPatientInfo(patientId, onSuccess = {
            mTheDB.patientDao().addPatient(it)
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun addNewConsultationRequestToDB(speciality: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getNewConsultationRequest(onSuccess = {
            if (it.isNotEmpty()){
                val requestList = arrayListOf<ConsultationRequestVO>()
                for (request in it){
                    if (speciality==request.speciality && request.status== REQUEST_STATUS_REQUEST){
                        requestList.add(request)
                    }
                }
                mTheDB.consultationRequestDao().deleteAllConsultationRequests()
                mTheDB.consultationRequestDao().addAllConsultationRequests(requestList)
                onSuccess()
            }
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun addConsultationListToDoctorDB(doctorId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getConsultationList(onSuccess = {
            if (it.isNotEmpty()){
                val consultations = arrayListOf<ConsultationVO>()
                for (consultation in it){
                    if (doctorId == consultation.doctorInfo.doctorId){
                        consultations.add(consultation)
                    }
                }
                mTheDB.consultationDao().deleteAllConsultations()
                mTheDB.consultationDao().addAllConsultations(consultations)
            }
            onSuccess()
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun addConsultationListToPatientDB(
        patientId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultationList(onSuccess = {
            if (it.isNotEmpty()){
                val consultations = arrayListOf<ConsultationVO>()
                for (consultation in it){
                    if (patientId == consultation.patientInfo.patientId){
                        consultations.add(consultation)
                    }
                }
                mTheDB.consultationDao().deleteAllConsultations()
                mTheDB.consultationDao().addAllConsultations(consultations)
            }
            onSuccess()
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun getConsultationByIdAndSaveToDB(
        consultationId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultationById(consultationId, onSuccess={
            if (it!=null){
                mTheDB.consultationDao().addConsultation(it)
                onSuccess()
            }
        }, onFailure={
            onFailure(it)
        })
    }


    override fun addCheckoutInfoToDB(checkoutId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getCheckoutInfo(checkoutId, onSuccess = {
            mTheDB.checkoutDao().addNewCheckout(it)
            onSuccess()
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun addRecentDoctorsToDB(
        ids: ArrayList<String>,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
//        for(id in ids){
//            mFirebaseApi.getDoctorInfo(id, onSuccess = {
//                mTheDB.doctorDao().deleteAllDoctors()
//                mTheDB.doctorDao().addDoctor(it)
//            }, onFailure = {})
//        }

        mFirebaseApi.getDoctorListByIds(ids, onSuccess = {
            mTheDB.doctorDao().deleteAllDoctors()
            mTheDB.doctorDao().addAllDoctors(it)
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun getChatMessagesAndSaveToDB(
        consultationVO: ConsultationVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getChatMessages(consultationVO.consultationId, onSuccess = {
            if (it!=null && it.isNotEmpty()){
                consultationVO.chats = it
                mTheDB.consultationDao().addConsultation(consultationVO)
            }
            onSuccess()
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun getMedicationListAndSaveToDB(
        consultationVO: ConsultationVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getMedicationList(consultationVO.consultationId, onSuccess = {
            if (it!=null && it.isNotEmpty()){
                consultationVO.prescriptions = it
                mTheDB.consultationDao().addConsultation(consultationVO)
            }
            onSuccess()
        }, onFailure = {
            onFailure(it)
        })
    }

//    override fun getNewConsultationAndSaveToDB(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
//        mFirebaseApi.getNewConsultation(onSuccess = {
//            if (it.isNotEmpty()){
//                val consultations = arrayListOf<ConsultationVO>()
//                for (consultation in it){
//                    if (mPatientId == consultation.patientInfo.patientId){
//                        consultations.add(consultation)
//                    }
//                }
//
//            }
//        }, onFailure = {
//            onFailure(it)
//        })
//    }

    override fun addNewDoctor(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addNewDoctor(doctorVO,  onSuccess, onFailure)
    }



    override fun addNewPatient(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addNewPatient(patientVO, onSuccess, onFailure)
    }

    override fun updateDoctorInfo(
        doctorVO: DoctorVO,
        image: Bitmap,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.updateDoctorInfo(doctorVO, image, onSuccess, onFailure)
    }

    override fun updatePatientInfo(
        patientVO: PatientVO,
        image: Bitmap,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.updatePatientInfo(patientVO, image, onSuccess, onFailure)
    }

    override fun addConsultationRequest(
        consultationRequestVO: ConsultationRequestVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addConsultationRequest(consultationRequestVO, onSuccess, onFailure)
    }

    override fun updateStatusConsultationRequest(
        requestId: String,
        status: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.updateStatusConsultationRequest(requestId, status, onSuccess, onFailure)
    }

    override fun addNewConsultation(
        consultationVO: ConsultationVO,
        onSuccess: (consultationId:String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addNewConsultation(consultationVO, onSuccess, onFailure)
    }

    override fun addMedicationListToConsultation(
        consultationId: String,
        medications: ArrayList<MedicationVO>
    ) {
        mFirebaseApi.addMedicationListToConsultation(consultationId, medications)
    }

    override fun addNoteToConsultation(
        consultationId: String,
        note: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addNoteToConsultation(consultationId, note, onSuccess, onFailure)
    }

    override fun finishConsultation(
        consultationId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.finishConsultation(consultationId, onSuccess, onFailure)
    }

    override fun addChatTextMessage(
        consultationId: String,
        sender: String,
        message: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addChatTextMessage(consultationId, sender, message, onSuccess, onFailure)
    }

    override fun addChatImageMessage(
        consultationId: String,
        sender: String,
        image: Bitmap,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addChatImageMessage(consultationId, sender, image, onSuccess, onFailure)
    }

    override fun addCheckout(
        checkoutVO: CheckoutVO,
        onSuccess: (id: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addCheckout(checkoutVO, onSuccess, onFailure)
    }

    override fun addAddressToCheckout(
        checkoutId: String,
        deliveryVO: DeliveryVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addAddressToCheckout(checkoutId, deliveryVO, onSuccess, onFailure)
    }

    override fun addRecentlyConsultedDoctor(
        doctorId: String,
        patientId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addRecentlyConsultedDoctor(doctorId, patientId, onSuccess, onFailure)
    }

    override fun getConsultationRequests(onFailure: (String) -> Unit): LiveData<List<ConsultationRequestVO>> {
        return mTheDB.consultationRequestDao().getAllConsultationRequests()
    }

    override fun getConsultationRequestById(requestId: String, onFailure: (String) -> Unit): LiveData<ConsultationRequestVO> {
        return mTheDB.consultationRequestDao().getConsultationRequestById(requestId)
    }

    override fun getConsultationList(onFailure: (String) -> Unit): LiveData<List<ConsultationVO>> {
        return mTheDB.consultationDao().getAllConsultations()
    }

    override fun getConsultationById(
        consultationId: String,
        onFailure: (String) -> Unit
    ): LiveData<ConsultationVO> {
        return mTheDB.consultationDao().getConsultationById(consultationId)
    }

    override fun getCheckoutInfo(
        checkoutId: String,
        onFailure: (String) -> Unit
    ): LiveData<CheckoutVO> {
        return mTheDB.checkoutDao().getCheckoutById(checkoutId)
    }

    override fun getDoctorsList(onFailure: (String) -> Unit): LiveData<List<DoctorVO>> {
        return mTheDB.doctorDao().getAllDoctors()
    }

    override fun getDoctorById(id: String, onFailure: (String) -> Unit): LiveData<DoctorVO> {
        return mTheDB.doctorDao().getDoctorById(id)
    }

    override fun getPatientById(id: String, onFailure: (String) -> Unit): LiveData<PatientVO> {
        return mTheDB.patientDao().getPatientById(id)
    }

    override fun getAllSpecialities(onFailure: (String) -> Unit): LiveData<List<SpecialityVO>> {
        return mTheDB.specialityDao().getAllSpecialities()
    }

    override fun getSpeciality(
        speciality: String,
        onFailure: (String) -> Unit
    ): LiveData<SpecialityVO> {
        return mTheDB.specialityDao().getSpeciality(speciality)
    }

    override fun getRecentlyConsultedDoctorIds(
        patientId: String,
        onFailure: (String) -> Unit
    ): LiveData<ArrayList<String>> {
        //return mTheDB.patientDao().getRecentDoctors(patientId)
        return MutableLiveData<ArrayList<String>>()
    }

    override fun getRecentlyConsultedDoctorList(onFailure: (String) -> Unit): LiveData<List<DoctorVO>> {
        return mTheDB.doctorDao().getAllDoctors()
    }
}