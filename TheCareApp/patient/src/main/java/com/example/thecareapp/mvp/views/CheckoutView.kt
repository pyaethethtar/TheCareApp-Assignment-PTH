package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.CheckoutVO
import com.example.shared.mvp.BaseView

interface CheckoutView : BaseView {

    fun displayCheckoutInfo(checkout: CheckoutVO)
    fun displayCheckoutDialog()
}