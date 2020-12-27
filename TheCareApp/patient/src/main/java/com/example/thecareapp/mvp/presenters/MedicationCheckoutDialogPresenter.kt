package com.example.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.MedicationCheckoutDialogView

interface MedicationCheckoutDialogPresenter  :BasePresenter<MedicationCheckoutDialogView> {

    fun onUiReady(checkoutId: String, lifecycleOwner: LifecycleOwner)
    fun onTapPay()
}