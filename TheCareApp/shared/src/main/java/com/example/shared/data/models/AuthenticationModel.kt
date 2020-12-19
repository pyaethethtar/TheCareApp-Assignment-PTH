package com.example.shared.data.models

import com.example.shared.network.auth.AuthManagerApi
import com.facebook.AccessToken

interface AuthenticationModel {

    var mAuthManager : AuthManagerApi

    fun register(username: String, email: String, password: String,
                 onSuccess:(id: String)->Unit, onFailure:(String)->Unit)

    fun login(phone: String, password: String, onSuccess: (id: String) -> Unit, onFailure: (String) -> Unit)

    fun loginWithFacebook(token: AccessToken, onSuccess: (id: String, name: String, email: String) -> Unit,
                          onFailure: (String) -> Unit)

    fun updateProfile(password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
}