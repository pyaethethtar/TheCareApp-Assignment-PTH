package com.example.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.GeneralQuestionsNewView

interface GeneralQuestionsNewPresenter : BasePresenter<GeneralQuestionsNewView> {

    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapNext(generalCasesummary: ArrayList<CaseSummaryVO>)
}