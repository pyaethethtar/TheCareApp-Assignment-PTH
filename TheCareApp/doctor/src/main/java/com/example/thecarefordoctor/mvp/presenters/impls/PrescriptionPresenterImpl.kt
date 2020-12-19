package com.example.thecarefordoctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.PrescriptionPresenter
import com.example.thecarefordoctor.mvp.views.PrescriptionView

class PrescriptionPresenterImpl : PrescriptionPresenter,
    AbstractBasePresenter<PrescriptionView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        mCareModel.getSpeciality(mLoginedDoctor.speciality, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mView?.displayMedicines(it.medication)
            }
        })
    }

    override fun onTapMedicine(medicine: String) {
        mView?.displayPrescriptionDialog(medicine)
    }

    override fun onTapSearch() {
        TODO("Not yet implemented")
    }

    override fun onTapFinish() {
        TODO("Not yet implemented")
    }


}