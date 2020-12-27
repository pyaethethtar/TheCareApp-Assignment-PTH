package com.example.shared.mvp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.google.android.gms.common.data.DataBufferObserverSet

class PatientInfoDetailsPresenterImpl : PatientInfoDetailsPresenter, AbstractBasePresenter<PatientInfoDetailsView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl

    override fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner) {
        mCareModel.getConsultationById(consultationId, onFailure ={
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mView?.displayPatientInfo(it.patientInfo.patientName, it.caseSummary)
            }
        })
    }

    override fun onTapBack() {
       mView?.backToChatScreen()
    }
}