package com.example.thecarefordoctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.ProfilePresenter
import com.example.thecarefordoctor.mvp.views.ProfileView

class ProfilePresenterImpl : ProfilePresenter, AbstractBasePresenter<ProfileView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl

    override fun onUiReady(doctorId: String, lifecycleOwner: LifecycleOwner) {
        mCareModel.getDoctorById(doctorId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mView?.displayDoctorInfo(it)
            }
        })
    }

    override fun onTapEdit(doctorId: String) {
        mView?.navigateToProfileEdit(doctorId)
    }

    override fun onTapBack() {
        mView?.navigateToMainScreen()
    }

    override fun onTapSignOut() {
        TODO("Not yet implemented")
    }

    override fun onTapChangePw() {
        TODO("Not yet implemented")
    }
}