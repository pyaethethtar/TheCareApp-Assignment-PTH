package com.example.thecareapp.mvp.presenters.impls

import android.util.Log
import com.example.shared.data.models.AuthenticationModel
import com.example.shared.data.models.AuthenticationModelImpl
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.R
import com.example.thecareapp.mvp.presenters.LoginPresenter
import com.example.thecareapp.mvp.views.LoginView
import com.facebook.AccessToken
import com.facebook.Profile

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthModel : AuthenticationModel = AuthenticationModelImpl

    override fun onTapLogin(phone: String, password: String) {
        mAuthModel.login(phone, password, onSuccess = {id->
            mView?.navigateToMainScreen(id)
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }

    override fun onTapSignInWithFacebook() {
        mView?.sendRequestFacebookLogin()
    }

    override fun onSuccessFacebookLogin(token: AccessToken) {
        mAuthModel.loginWithFacebook(token, onSuccess = {id, name, email->
            val image = Profile.getCurrentProfile().getProfilePictureUri(R.dimen.size_profile_patient, R.dimen.size_profile_patient)
            mView?.navigateToRegisterScreen(id, name, email, image.toString())
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }


}