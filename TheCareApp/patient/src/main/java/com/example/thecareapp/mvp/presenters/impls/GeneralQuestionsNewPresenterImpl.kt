package com.example.thecareapp.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.GeneralQuestionsNewPresenter
import com.example.thecareapp.mvp.views.GeneralQuestionsNewView

class GeneralQuestionsNewPresenterImpl : GeneralQuestionsNewPresenter,
    AbstractBasePresenter<GeneralQuestionsNewView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private lateinit var mLifecycleOwner: LifecycleOwner

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        mLifecycleOwner = lifecycleOwner
    }


    override fun onTapNext(generalCasesummary: ArrayList<CaseSummaryVO>) {
        mCareModel.consultationRequestVO.caseSummary = generalCasesummary
        mView?.navigateToSpecialQuestioins()
    }
}