package com.example.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.MedicationCheckoutDialogPresenter
import com.example.thecareapp.mvp.views.MedicationCheckoutDialogView

class MedicationCheckoutDialogPresenterImpl : MedicationCheckoutDialogPresenter,
    AbstractBasePresenter<MedicationCheckoutDialogView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl

    override fun onUiReady(checkoutId: String, lifecycleOwner: LifecycleOwner) {
        mCareModel.getCheckoutInfo(checkoutId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mView?.displayCheckoutInfo(it)
            }
        })
    }

    override fun onTapPay() {
        mView?.navigatetoChatScreen()
    }


}