package com.example.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.shared.utils.REQUEST_STATUS_REQUEST
import com.example.thecareapp.mvp.presenters.CaseSummaryPresenter
import com.example.thecareapp.mvp.views.CaseSummaryView

class CaseSummaryPresenterImpl : CaseSummaryPresenter, AbstractBasePresenter<CaseSummaryView>() {

    private val mCareModel: TheCareModel = TheCareModelImpl
    private val consultationRequestVO = ConsultationRequestVO()
    private var mPatientVO = PatientVO()
    private lateinit var mSpeciality : String
    private lateinit var mLifecycleOwner: LifecycleOwner

    override fun onUiReady(patientId: String, speciality: String, lifecycleOwner: LifecycleOwner) {
        mSpeciality = speciality
        mLifecycleOwner = lifecycleOwner

        consultationRequestVO.speciality = speciality
        mCareModel.getPatientById(patientId, onFailure = {})
            .observe(lifecycleOwner, Observer {
                if (it!=null){
                    mPatientVO = it
                }
            })


        consultationRequestVO.patientInfo = mPatientVO
        mPatientVO.patientCaseSummary?.let {
            if (it.isNotEmpty()){
                mView?.navigateToGeneralQuestions(mPatientVO)
            }
            else{
                mView?.navigateToGeneralQuestionsNew()
            }
        }.run {
            mView?.navigateToGeneralQuestionsNew()
        }
    }


    override fun onTapNext(generalCaseSummary: ArrayList<CaseSummaryVO>) {
        consultationRequestVO.caseSummary = generalCaseSummary
        mCareModel.getSpeciality(mSpeciality, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(mLifecycleOwner, Observer {
            if (it!=null){
                mView?.navigateToSpecialQuestions(it.specialQuestions)
            }
        })
    }

    override fun onTapContinue(specialCaseSummary: ArrayList<CaseSummaryVO>) {
        consultationRequestVO.caseSummary.addAll(specialCaseSummary)
        mView?.displayPatientInfoConfirmatioinDialog(consultationRequestVO)
    }

    override fun onTapCreateConsultationRequest() {
        consultationRequestVO.status = REQUEST_STATUS_REQUEST
        mCareModel.addConsultationRequest(consultationRequestVO, onSuccess = {
            mView?.navigateToMainScreen()
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }


}