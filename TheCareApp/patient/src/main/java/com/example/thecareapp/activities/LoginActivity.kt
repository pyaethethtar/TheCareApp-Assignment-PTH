package com.example.thecareapp.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.shared.activities.BaseActivity
import com.example.shared.utils.LOGIN_KEY
import com.example.shared.utils.PATIENT_KEY
import com.example.shared.utils.PREFERENCE_FILE_KEY
import com.example.thecareapp.R
import com.example.thecareapp.mvp.presenters.LoginPresenter
import com.example.thecareapp.mvp.presenters.impls.LoginPresenterImpl
import com.example.thecareapp.mvp.views.LoginView
import com.facebook.*
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), LoginView {

    private val callbackManager = CallbackManager.Factory.create()
    private lateinit var mPresenter : LoginPresenter
    private lateinit var sharedPref : SharedPreferences
    private var isLogin = false
    private var mPatientId : String ?= "patient_id"


    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPref = getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        isLogin = sharedPref.getBoolean(LOGIN_KEY, false)
        mPatientId = sharedPref.getString(PATIENT_KEY, "patient_id")
        if (isLogin){
            navigateToMainScreen(mPatientId?:"patient_id")
        }
        else{
            setUpPresenter()
            setUpListeners()
        }



//        val accessToken = AccessToken.getCurrentAccessToken()
//        val isLoggedIn = accessToken != null && !accessToken.isExpired


    }





    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(LoginPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListeners(){
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(etPhone.text.toString(), etPassword.text.toString())
        }
        btnFacebookSignin.setOnClickListener {
            mPresenter.onTapSignInWithFacebook()
        }
    }


    override fun navigateToMainScreen(userId: String) {
        isLogin = true
        mPatientId = userId
        startActivity(MainActivity.newIntent(userId, this))
    }

    override fun navigateToRegisterScreen(id: String, name: String, email: String, image: String) {
        startActivity(RegisterActivity.newIntent(id, name, email, image, this))
    }

    override fun sendRequestFacebookLogin() {
        btnFacebookSignin.setReadPermissions("email", "public_profile")
        btnFacebookSignin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                mPresenter.onSuccessFacebookLogin(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d("FacebookSuccess", "facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d("FacebookSuccess", "facebook:onError", error)
            }
        })
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onPause() {
        super.onPause()

        val preferenceEditor = sharedPref.edit()
        preferenceEditor.putBoolean(LOGIN_KEY, isLogin)
        preferenceEditor.putString(PATIENT_KEY, mPatientId)
        preferenceEditor.apply()
    }
}