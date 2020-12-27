package com.example.thecareapp.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.ProfilePresenter
import com.example.thecareapp.mvp.views.ProfileView

class ProfilePresenterImpl : ProfilePresenter, AbstractBasePresenter<ProfileView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl

    override fun onUiReady(patientId: String, lifecycleOwner: LifecycleOwner) {
        mCareModel.getPatientById(patientId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mView?.displayPatientInfo(it)

                it.patientCaseSummary?.let {casesummary->
                    if (casesummary.isEmpty()){
                        mView?.displayEmptyDialog()
                    }
                }
            }
        })
    }

    override fun onTapEdit(patientId: String) {
        mView?.navigateToProfileEdit(patientId)
    }

    override fun onTapBack() {
        Log.d("TheCareMM", "navigateToMainScreen")
        //mView?.navigateToMainScreen()
    }

    override fun onTapSignOut() {
        TODO("Not yet implemented")
    }

    override fun onTapChangePw() {
        TODO("Not yet implemented")
    }
}