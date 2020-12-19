package com.example.thecarefordoctor.mvp.presenters.impls

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.shared.data.models.AuthenticationModel
import com.example.shared.data.models.AuthenticationModelImpl
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.RegisterPasswordPresenter
import com.example.thecarefordoctor.mvp.views.RegisterPasswordView

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
    ){

        mAuthModel.updateProfile(password, onSuccess = {
            Log.d("Firebase", "Password updated successfully!")
        }, onFailure = {
            mView?.showErrorMessage(it)
        })

        val doctorVO = DoctorVO()
        doctorVO.doctorId = id
        doctorVO.doctorName = name
        doctorVO.doctorEmail = email
        doctorVO.doctorProfileImage = image
        doctorVO.doctorPassword = password
        doctorVO.doctorPhone = phone

        mCareModel.addNewDoctor(doctorVO, onSuccess = {
                mView?.navigateToDoctorInfoScreen(doctorVO)
            }, onFailure = {
                mView?.showErrorMessage(it)
            })

    }

}