package com.example.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.GeneralQuestionsPresenter
import com.example.thecareapp.mvp.views.GeneralQuestionsView

class GeneralQuestionsPresenterImpl : GeneralQuestionsPresenter, AbstractBasePresenter<GeneralQuestionsView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl

    override fun onUiReady() {
        mView?.displayPatientInfo()
    }

    override fun onTapNext(generalCasesummary: ArrayList<CaseSummaryVO>) {
        mCareModel.consultationRequestVO.caseSummary = generalCasesummary
        mView?.navigateToSpecialQuestioins()
    }


}