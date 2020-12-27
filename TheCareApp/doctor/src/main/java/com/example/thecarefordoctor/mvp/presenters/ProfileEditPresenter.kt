package com.example.thecarefordoctor.mvp.presenters

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.mvp.views.ProfileEditView

interface ProfileEditPresenter : BasePresenter<ProfileEditView> {

    fun onUiReady(doctorId: String, lifecycleOwner: LifecycleOwner)
    fun onTapSave(doctorVO: DoctorVO, image: Bitmap)
    fun onTapBack()
}