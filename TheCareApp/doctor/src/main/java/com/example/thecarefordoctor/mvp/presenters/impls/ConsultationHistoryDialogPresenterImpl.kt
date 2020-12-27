package com.example.thecarefordoctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.ConsultationHistoryDialogPresenter
import com.example.thecarefordoctor.mvp.views.ConsultationHistoryDialogView

class ConsultationHistoryDialogPresenterImpl : ConsultationHistoryDialogPresenter,
    AbstractBasePresenter<ConsultationHistoryDialogView>(){

    private val mCareModel : TheCareModel = TheCareModelImpl

    override fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner) {
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mView?.displayConsultationHistory(it)
            }
        })
    }


}