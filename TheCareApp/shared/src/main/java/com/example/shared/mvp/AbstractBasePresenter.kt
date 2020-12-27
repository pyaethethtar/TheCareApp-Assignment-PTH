package com.example.shared.mvp

import androidx.lifecycle.ViewModel
import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.PatientVO

abstract class AbstractBasePresenter<T: BaseView> : BasePresenter<T>, ViewModel() {

    var mView : T ?= null

    override fun initPresenter(view: T) {
        mView = view
    }

}