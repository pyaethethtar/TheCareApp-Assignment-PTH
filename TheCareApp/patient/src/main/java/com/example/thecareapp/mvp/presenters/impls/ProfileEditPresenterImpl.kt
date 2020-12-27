package com.example.thecareapp.mvp.presenters.impls

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.ProfileEditPresenter
import com.example.thecareapp.mvp.views.ProfileEditView

class ProfileEditPresenterImpl  :ProfileEditPresenter, AbstractBasePresenter<ProfileEditView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private lateinit var mPatientId: String
    private var mPatientVO = PatientVO()

    override fun onUiReady(patientId: String, lifecycleOwner: LifecycleOwner) {

        mCareModel.getPatientById(patientId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mPatientVO = it
                mView?.displayPatientInfo(it)
            }
        })
    }

    override fun onTapSave(patientVO: PatientVO, image: Bitmap) {
        patientVO.patientEmail = mPatientVO.patientEmail
        patientVO.patientPassword = mPatientVO.patientPassword

        mCareModel.updatePatientInfo(patientVO, image, onSuccess = {
            mCareModel.addPatientInfoToDB(patientVO.patientId, onSuccess = {}, onFailure = {})
            mView?.navigateToProfileScreen()
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }

    override fun onTapBack() {
        mView?.navigateToProfileScreen()
    }
}