package com.example.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.AuthenticationModel
import com.example.shared.data.models.AuthenticationModelImpl
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.MainPresenter
import com.example.thecareapp.mvp.views.MainView

class MainPresenterImpl : MainPresenter , AbstractBasePresenter<MainView>(){

    private val mCareModel : TheCareModel = TheCareModelImpl

    override fun onUiReady(patientId: String, lifecycleOwner: LifecycleOwner) {
        mCareModel.getDataFromApiAndSaveToPatientDB(patientId, onSuccess = {}, onFailure = {})
        mView?.navigateToHomeScreen()

    }

    override fun onTapHome() {
        mView?.navigateToHomeScreen()
    }

    override fun onTapConsultationList() {
        mView?.navigateToConsultationScreen()
    }

    override fun onTapProfile() {
        mView?.navigateToProfileScreen()
    }


}