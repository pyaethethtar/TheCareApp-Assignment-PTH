package com.example.thecarefordoctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.mvp.views.ProfileView

interface ProfilePresenter : BasePresenter<ProfileView> {

    fun onUiReady(doctorId: String, lifecycleOwner: LifecycleOwner)
    fun onTapEdit(doctorId: String)
    fun onTapBack()
    fun onTapSignOut()
    fun onTapChangePw()
}