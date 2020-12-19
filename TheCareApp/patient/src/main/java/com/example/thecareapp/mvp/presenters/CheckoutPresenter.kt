package com.example.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.CheckoutView

interface CheckoutPresenter: BasePresenter<CheckoutView> {

    fun onUiReady(checkoutId: String, lifecycleOwner: LifecycleOwner)
    fun onTapCheckout(address : String)
}