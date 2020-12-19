package com.example.thecareapp.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.shared.BaseActivity
import com.example.thecareapp.R
import com.example.thecareapp.fragments.RegisterPasswordFragment
import com.example.thecareapp.fragments.RegisterPhoneFragment
import com.example.thecareapp.mvp.presenters.RegisterPresenter
import com.example.thecareapp.mvp.presenters.impls.RegisterPresenterImpl
import com.example.thecareapp.mvp.views.RegisterView
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : BaseActivity(), RegisterView {

    private lateinit var mPresenter : RegisterPresenter
    private var mPatientId : String = ""
    private var mPatientName : String = ""
    private var mPatientEmail : String = ""
    private var mPatientImage : String = ""

    companion object{
        const val PATIENT_ID_EXTRA = "PATIENT_ID_EXTRA"
        const val PATIENT_NAME_EXTRA = "PATIENT_NAME_EXTRA"
        const val PATIENT_EMAIL_EXTRA = "PATIENT_EMAIL_EXTRA"
        const val PATIENT_IMAGE_EXTRA = "PATIENT_IMAGE_EXTRA"
        fun newIntent(id: String, name: String, email: String, image: String, context: Context): Intent{
            val intent = Intent(context, RegisterActivity::class.java)
            intent.putExtra(PATIENT_ID_EXTRA, id)
            intent.putExtra(PATIENT_NAME_EXTRA, name)
            intent.putExtra(PATIENT_EMAIL_EXTRA, email)
            intent.putExtra(PATIENT_IMAGE_EXTRA, image)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPatientId = intent.getStringExtra(PATIENT_ID_EXTRA) ?: ""
        mPatientName = intent.getStringExtra(PATIENT_NAME_EXTRA) ?: ""
        mPatientEmail = intent.getStringExtra(PATIENT_EMAIL_EXTRA) ?: ""
        mPatientImage = intent.getStringExtra(PATIENT_IMAGE_EXTRA) ?: ""

        setUpPresenter()
        mPresenter.onUiReady(mPatientName, mPatientImage)
        openFragment(RegisterPhoneFragment.newInstance(mPatientId, mPatientName, mPatientEmail, mPatientImage))

        btnLogin.setOnClickListener {
            mPresenter.onTapLogin()
        }

    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(RegisterPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    fun openFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
    }

    override fun displayPatientImageAndName(name: String, image: String) {
        Glide.with(this).load(image).circleCrop().into(ivPatientProfile)
        tvName.text = name
    }

    override fun navigateToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
    }


}