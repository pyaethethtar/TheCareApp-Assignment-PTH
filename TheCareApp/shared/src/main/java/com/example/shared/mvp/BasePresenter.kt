package com.example.shared.mvp

import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.PatientVO


interface BasePresenter<T : BaseView> {

    fun initPresenter(view: T)
}