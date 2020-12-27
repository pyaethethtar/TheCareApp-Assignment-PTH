package com.example.thecareapp.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.SpecialQuestionsPresenter
import com.example.thecareapp.mvp.views.SpecialQuestionsView
import java.lang.StringBuilder

class SpecialQuestionsPresenterImpl : SpecialQuestionsPresenter, AbstractBasePresenter<SpecialQuestionsView>() {

    private val mCareModel: TheCareModel = TheCareModelImpl
    private var casesummaryList = arrayListOf<CaseSummaryVO>()
    private var mPosition: Int = -1

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        val speciality = mCareModel.consultationRequestVO.speciality

        mCareModel.getSpeciality(speciality, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null && it.specialQuestions.isNotEmpty()){
                mView?.displaySpecialQuestions(it.specialQuestions)
            }
        })
    }

    override fun onAnswerSpecialQuestion(position: Int, casesummary: CaseSummaryVO) {
        if (mPosition==position)    casesummaryList.set(position, casesummary)
        else{
            casesummaryList.add(position, casesummary)
            mPosition++
        }

    }

    override fun onTapContinue() {
        mCareModel.consultationRequestVO.caseSummary.addAll(casesummaryList)
        mView?.navigateToPatientInfoConfirmation()
    }
}