package com.example.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.ProfileView

interface ProfilePresenter : BasePresenter<ProfileView> {

    fun onUiReady(patientId: String, lifecycleOwner: LifecycleOwner)
    fun onTapEdit(patientId: String)
    fun onTapBack()
    fun onTapSignOut()
    fun onTapChangePw()
}