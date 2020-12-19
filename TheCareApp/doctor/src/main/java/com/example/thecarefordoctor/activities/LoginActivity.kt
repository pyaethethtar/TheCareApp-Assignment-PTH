package com.example.thecarefordoctor.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.shared.BaseActivity
import com.example.shared.utils.DOCTOR_KEY
import com.example.shared.utils.LOGIN_KEY
import com.example.shared.utils.PREFERENCE_FILE_KEY
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.mvp.presenters.LoginPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.LoginPresenterImpl
import com.example.thecarefordoctor.mvp.views.LoginView
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {

    private val callbackManager = CallbackManager.Factory.create()
    private lateinit var mPresenter : LoginPresenter

    private var mDoctorId : String ?= "doctor_id"
    private lateinit var sharedPref : SharedPreferences
    private var isLogin = false

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPref = getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        isLogin = sharedPref.getBoolean(LOGIN_KEY, false)
        mDoctorId = sharedPref.getString(DOCTOR_KEY, "doctor_id")
        if (isLogin){
            navigateToMainScreen(mDoctorId?:"patient_id")
        }
        else{
            setUpPresenter()
            setUpListeners()
        }

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
        mDoctorId = userId
        startActivity(MainActivity.newIntent(userId,this))
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

    override fun navigateToRegisterScreen(id: String, name: String, email: String, image: String) {
        startActivity(RegisterActivity.newIntent(id, name, email, image, this))
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
        preferenceEditor.putString(DOCTOR_KEY, mDoctorId)
        preferenceEditor.apply()
    }
}