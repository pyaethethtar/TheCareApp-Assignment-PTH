package com.example.shared.network.auth

import android.app.Activity
import android.net.Uri
import com.facebook.AccessToken

interface AuthManagerApi {

    fun register(username: String, email: String, password: String,
                 onSuccess:(id: String)->Unit, onFailure:(String)->Unit)
    fun login(email: String, password: String, onSuccess: (id: String) -> Unit, onFailure: (String) -> Unit)

    fun loginWithFacebook(token: AccessToken, onSuccess: (id: String, name:String, email:String) -> Unit, onFailure: (String) -> Unit)

    fun loginWithPhone(phone : String, activity: Activity)

    fun updateProfile(password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
}