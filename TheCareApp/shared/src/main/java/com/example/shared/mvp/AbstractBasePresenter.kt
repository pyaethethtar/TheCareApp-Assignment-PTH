package com.example.shared.mvp

import androidx.lifecycle.ViewModel
import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.PatientVO

abstract class AbstractBasePresenter<T: BaseView> : BasePresenter<T>, ViewModel() {

    var mView : T ?= null
    var mLoginedPatient : PatientVO ?= null
    var mLoginedDoctor : DoctorVO = DoctorVO()

    override fun initPresenter(view: T) {
        mView = view
    }

}