package com.example.thecareapp.mvp.presenters

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.ProfileEditView

interface ProfileEditPresenter : BasePresenter<ProfileEditView> {

    fun onUiReady(patientId: String, lifecycleOwner: LifecycleOwner)
    fun onTapSave(patientVO: PatientVO, image: Bitmap)
    fun onTapBack()
}