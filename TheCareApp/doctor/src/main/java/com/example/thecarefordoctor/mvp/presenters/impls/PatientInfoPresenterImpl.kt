package com.example.thecarefordoctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.PatientInfoPresenter
import com.example.thecarefordoctor.mvp.views.PatientInfoView

class PatientInfoPresenterImpl : PatientInfoPresenter, AbstractBasePresenter<PatientInfoView>() {
    override fun onUiReady(patientId: String, lifecycleOwner: LifecycleOwner) {
        TODO("Not yet implemented")
    }

    override fun onTapStartConsultation() {
        TODO("Not yet implemented")
    }

    override fun onTapBack() {
        TODO("Not yet implemented")
    }

//    private val mCareModel : TheCareModel = TheCareModelImpl
//    private var mPatientInfo = PatientVO()
//    private var mCaseSummary = arrayListOf<CaseSummaryVO>()
//
//    override fun onUiReady(patientId: String, lifecycleOwner: LifecycleOwner) {
//        mCareModel.getConsultationRequest(onFailure = {
//            mView?.showErrorMessage(it)
//        }).observe(lifecycleOwner, Observer {
//            if (it!= null){
//                mPatientInfo = it.patientInfo
//                mCaseSummary = it.caseSummary
//                mView?.displayPatientInfo(mPatientInfo)
//                mView?.displayCaseSummary(mCaseSummary)
//            }
//        })
//    }
//
//    override fun onTapStartConsultation() {
//        val consultationVO = ConsultationVO()
//        consultationVO.patientInfo = mPatientInfo
//        consultationVO.doctorInfo = mLoginedDoctor
//        consultationVO.caseSummary = mCaseSummary
//
//        mCareModel.addNewConsultation(consultationVO, onSuccess = {
//            mView?.navigateToChatScreen()
//        }, onFailure = {
//            mView?.showErrorMessage(it)
//        })
//    }
//
//    override fun onTapBack() {
//
//    }


}