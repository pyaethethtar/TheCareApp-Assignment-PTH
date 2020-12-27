package com.example.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.CheckoutVO
import com.example.shared.data.vos.DeliveryVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.CheckoutPresenter
import com.example.thecareapp.mvp.views.CheckoutView

class CheckoutPresenterImpl : CheckoutPresenter, AbstractBasePresenter<CheckoutView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private var mCheckoutId : String = ""

    override fun onUiReady(checkoutId: String, lifecycleOwner: LifecycleOwner) {
        mCheckoutId = checkoutId

        mCareModel.getCheckoutInfo(checkoutId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mView?.displayCheckoutInfo(it)
                mView?.displayNewAddressViewpod()
            }
        })
    }

    override fun onTapCheckout(address: String) {
        val deliveryVO = DeliveryVO()
        deliveryVO.address = address
        deliveryVO.fee = 3000

        mCareModel.addAddressToCheckout(mCheckoutId, deliveryVO, onSuccess = {
            mCareModel.addCheckoutInfoToDB(mCheckoutId, onSuccess = {}, onFailure = {})
            mView?.displayCheckoutDialog()
        }, onFailure = {
            mView?.showErrorMessage(it)
        })

    }
}