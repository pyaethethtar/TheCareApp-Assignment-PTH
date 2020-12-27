package com.example.thecarefordoctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.shared.utils.CONSULTATION_STATUS_END
import com.example.shared.utils.CONSULTATION_STATUS_START
import com.example.shared.utils.REQUEST_STATUS_ACCEPT
import com.example.thecarefordoctor.mvp.presenters.MainPresenter
import com.example.thecarefordoctor.mvp.views.MainView
import java.text.SimpleDateFormat
import java.util.*

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private lateinit var doctor: DoctorVO
    private lateinit var mLifecycleOwner: LifecycleOwner
    private lateinit var mSpeciality: String

    override fun onUiReady(doctorId: String, lifecycleOwner: LifecycleOwner) {
        mLifecycleOwner = lifecycleOwner

        mCareModel.getDoctorById(doctorId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it==null){
                mCareModel.getDataFromApiAndSaveToDoctorDB(doctorId, onSuccess = {}, onFailure = {})
            }
            else{
                doctor = it
                mCareModel.mSpeciality = it.speciality
                mView?.displayDoctorInfo(it)
            }
        })


        //requestDoctorInfo(doctorId, lifecycleOwner)
        mCareModel.addNewConsultationRequestToDB(mCareModel.mSpeciality, onSuccess = {}, onFailure = {})
        mCareModel.addConsultationListToDoctorDB(doctorId, onSuccess = {}, onFailure = {})

        requestConsultationList(lifecycleOwner)

    }

    override fun onObserveConsultationRequest(lifecycleOwner: LifecycleOwner) {
        mCareModel.getConsultationRequests(onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it.isNotEmpty()){
                mView?.displayNewRequests(it)
            }
            else{
                mView?.displayEmptyRequest()
            }
        })
    }

    override fun onTapProfile() {
        mView?.navigateToDoctorProfile(doctor.doctorId)
    }

    override fun onTapConsultationRequest(requestId: String) {
        mCareModel.getConsultationRequestById(requestId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(mLifecycleOwner, Observer {
            if (it!=null){
                mView?.navigateToPatientCaseSummary(it)
            }
        })
    }

    override fun onTapAccept(consultationRequest: ConsultationRequestVO) {
        mCareModel.updateStatusConsultationRequest(consultationRequest.requestId, REQUEST_STATUS_ACCEPT, onSuccess = {
            val consultationVO = ConsultationVO()
            consultationVO.doctorInfo = doctor
            consultationVO.patientInfo = consultationRequest.patientInfo
            consultationVO.caseSummary = consultationRequest.caseSummary
            consultationVO.consultationDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
            consultationVO.status = CONSULTATION_STATUS_START
            consultationVO.consultationNote = ""

            mCareModel.addNewConsultation(consultationVO, onSuccess = {
                mView?.navigateToChatScreen(it)
            }, onFailure = {
                mView?.showErrorMessage(it)
            })
        }, onFailure = {
            mView?.showErrorMessage(it)
        })



    }

    override fun onTapPostpone() {
        mView?.displayPostponeDialog()
    }

    override fun onTapLater() {
        TODO("Not yet implemented")
    }

    override fun onTapConsultationHistory(consultationId: String) {
        mView?.displayConsultationHistoryDialog(consultationId)
    }

    override fun onTapPrescription(consultationId: String) {
        mView?.displayPrescriptionDialog(consultationId)
    }

    override fun onTapNotes(consultationId: String) {
        mView?.navigateToNotesScreen(consultationId)
    }

    override fun onTapSendMessage(consultationId: String) {
        mView?.navigateToChatScreen(consultationId)
    }

    private fun requestDoctorInfo(doctorId: String, lifecycleOwner: LifecycleOwner){
        mCareModel.getDoctorById(doctorId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                doctor = it
                mCareModel.mSpeciality = it.speciality
                mView?.displayDoctorInfo(it)
            }
        })
    }

    private fun requestConsultationList( lifecycleOwner: LifecycleOwner){
        mCareModel.getConsultationList(onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it.isNotEmpty()){
                mView?.displayConsultationList(it)
            }
            else{
                mView?.displayEmptyConsultation()
            }
        })
    }



}