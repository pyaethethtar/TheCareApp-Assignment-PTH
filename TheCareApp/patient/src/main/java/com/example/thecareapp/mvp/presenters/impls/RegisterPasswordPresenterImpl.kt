package com.example.thecareapp.mvp.presenters.impls

import android.graphics.Bitmap
import android.util.Log
import com.example.shared.data.models.AuthenticationModel
import com.example.shared.data.models.AuthenticationModelImpl
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.shared.network.CloudFirestoreDataAgentImpl
import com.example.shared.network.auth.AuthManagerApi
import com.example.shared.network.auth.FirebaseAuthManager
import com.example.thecareapp.mvp.presenters.RegisterPasswordPresenter
import com.example.thecareapp.mvp.views.RegisterPasswordView

class RegisterPasswordPresenterImpl : RegisterPasswordPresenter, AbstractBasePresenter<RegisterPasswordView>() {

    private val mAuthModel : AuthenticationModel = AuthenticationModelImpl
    private val mCareModel : TheCareModel = TheCareModelImpl

    override fun onTapConfirm(
        id: String,
        name: String,
        email: String,
        image: String,
        phone: String,
        password: String
    ) {

        val patientVO = PatientVO()
        patientVO.patientId = id
        patientVO.patientName = name
        patientVO.patientEmail = email
        patientVO.patientProfileImage = image
        patientVO.patientPassword = password
        patientVO.patientPhone = phone

        mAuthModel.updateProfile(password, onSuccess = {
            Log.d("Firebase", "Password updated successfully!")
        }, onFailure = {
            mView?.showErrorMessage(it)
        })

        mCareModel.addNewPatient(patientVO, onSuccess = {
            mView?.navigateToMainScreen()
        }, onFailure = {
            mView?.showErrorMessage(it)
        })




    }


}