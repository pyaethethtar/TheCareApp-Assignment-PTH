package com.example.shared.data.models

import com.example.shared.network.auth.AuthManagerApi
import com.example.shared.network.auth.FirebaseAuthManager
import com.facebook.AccessToken

object AuthenticationModelImpl : AuthenticationModel {

    override var mAuthManager: AuthManagerApi = FirebaseAuthManager

    override fun register(
        username: String,
        email: String,
        password: String,
        onSuccess: (id: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.register(username, email, password, onSuccess, onFailure)
    }

    override fun login(
        phone: String,
        password: String,
        onSuccess: (id: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.login(phone, password, onSuccess, onFailure)
    }

    override fun loginWithFacebook(
        token: AccessToken,
        onSuccess: (id: String, name: String, email: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.loginWithFacebook(token, onSuccess, onFailure)
    }

    override fun updateProfile(
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.updateProfile(password, onSuccess, onFailure)
    }

}