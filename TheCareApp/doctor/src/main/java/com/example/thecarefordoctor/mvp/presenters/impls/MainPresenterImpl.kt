package com.example.thecarefordoctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.shared.utils.CONSULTATION_STATUS_START
import com.example.shared.utils.REQUEST_STATUS_ACCEPT
import com.example.thecarefordoctor.mvp.presenters.MainPresenter
import com.example.thecarefordoctor.mvp.views.MainView
import java.util.*

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private lateinit var doctor: DoctorVO
    private lateinit var mLifecycleOwner: LifecycleOwner

    override fun onUiReady(doctorId: String, lifecycleOwner: LifecycleOwner) {
        mLifecycleOwner = lifecycleOwner

        mCareModel.getDataFromApiAndSaveToDoctorDB(doctorId, onSuccess = {
        }, onFailure = {
            mView?.showErrorMessage(it)
        })

        requestDoctorInfo(doctorId, lifecycleOwner, onComplete = {speciality->
            mCareModel.addNewConsultationRequestToDB(speciality, onSuccess = {}, onFailure = {})
        })

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
        mView?.navigateToDoctorProfile(doctor)
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
        val consultationRequestVO = consultationRequest
        consultationRequestVO.status = REQUEST_STATUS_ACCEPT

        val consultationVO = ConsultationVO()
        consultationVO.doctorInfo = doctor
        consultationVO.patientInfo = consultationRequestVO.patientInfo
        consultationVO.caseSummary = consultationRequestVO.caseSummary
        consultationVO.consultationDate = Calendar.getInstance().time.toString()
        consultationVO.status = CONSULTATION_STATUS_START

        mCareModel.addConsultationRequest(consultationRequestVO, onSuccess = {
            mCareModel.addNewConsultationRequestToDB(doctor.speciality, onSuccess = {}, onFailure = {})
        }, onFailure = {})

        mCareModel.addNewConsultation(consultationVO, onSuccess = {
            mCareModel.addConsultationListToDoctorDB(consultationVO.doctorInfo.doctorId, onSuccess={
                mView?.navigateToChatScreen(consultationVO)
            }, onFailure={
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
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(mLifecycleOwner, Observer {
            mView?.displayConsultationHistoryDialog(it)
        })
    }

    override fun onTapPrescription(consultationId: String) {
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(mLifecycleOwner, Observer {
            mView?.displayPrescriptionDialog(it)
        })
    }

    override fun onTapNotes(consultationId: String) {
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(mLifecycleOwner, Observer {
            mView?.navigateToNotesScreen(it)
        })
    }

    override fun onTapSendMessage(consultationId: String) {
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(mLifecycleOwner, Observer {
            mView?.navigateToChatScreen(it)
        })
    }

    private fun requestDoctorInfo(doctorId: String, lifecycleOwner: LifecycleOwner, onComplete:(speciality: String)->Unit){
        mCareModel.getDoctorById(doctorId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                doctor = it
                mView?.displayDoctorInfo(it)
                onComplete(it.speciality)
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