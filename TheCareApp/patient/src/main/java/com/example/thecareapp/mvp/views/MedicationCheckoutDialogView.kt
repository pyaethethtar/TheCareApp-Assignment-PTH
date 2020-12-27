package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.CheckoutVO
import com.example.shared.mvp.BaseView

interface MedicationCheckoutDialogView : BaseView {

    fun displayCheckoutInfo(checkoutVO: CheckoutVO)
    fun navigatetoChatScreen()
}