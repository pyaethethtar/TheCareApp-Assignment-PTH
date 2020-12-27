package com.example.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.PrescriptionDialogPresenter
import com.example.thecareapp.mvp.views.PrescriptionDialogView

class PrescriptionDialogPresenterImpl : PrescriptionDialogPresenter, AbstractBasePresenter<PrescriptionDialogView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl

    override fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner) {
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                it.prescriptions?.let {prescriptions->
                    if (prescriptions.isNotEmpty())    mView?.displayPrescriptions(prescriptions)
                }
            }
        })
    }
}