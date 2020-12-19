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
            }
        })
    }

    override fun onTapCheckout(address: String) {
        val deliveryVO = DeliveryVO()
        deliveryVO.address = address
        val checkoutVO = CheckoutVO()
        checkoutVO.checkoutId = mCheckoutId
        checkoutVO.deliveryInfo = deliveryVO

        mCareModel.addCheckout(checkoutVO, onSuccess = {
            mView?.displayCheckoutDialog()
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }
}