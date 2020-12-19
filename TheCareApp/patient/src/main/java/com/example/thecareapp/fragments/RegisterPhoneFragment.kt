package com.example.thecareapp.fragments

import android.app.Activity
import android.content.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.shared.BaseFragment
import com.example.shared.network.auth.AuthManagerApi
import com.example.shared.network.auth.FirebaseAuthManager
import com.example.shared.utils.EM_CHECK_INTERNET_CONNECTION
import com.example.thecareapp.R
import com.example.thecareapp.activities.RegisterActivity
import com.example.thecareapp.mvp.presenters.RegisterPhonePresenter
import com.example.thecareapp.mvp.presenters.impls.RegisterPhonePresenterImpl
import com.example.thecareapp.mvp.views.RegisterPhoneView
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_register_phone_number.*

class RegisterPhoneFragment  :BaseFragment() , RegisterPhoneView{

    private lateinit var mPresenter : RegisterPhonePresenter
    private lateinit var mContext : Context
    private var mPatientId : String = ""
    private var mPatientName : String = ""
    private var mPatientEmail : String = ""
    private var mPatientImage : String = ""

    companion object{
        const val PATIENT_ID_EXTRA = "PATIENT_ID_EXTRA"
        const val PATIENT_NAME_EXTRA = "PATIENT_NAME_EXTRA"
        const val PATIENT_EMAIL_EXTRA = "PATIENT_EMAIL_EXTRA"
        const val PATIENT_IMAGE_EXTRA = "PATIENT_IMAGE_EXTRA"
        fun newInstance(id: String, name: String, email: String, image: String)=RegisterPhoneFragment().apply{
            arguments = Bundle().apply {
                putString(PATIENT_ID_EXTRA, id)
                putString(PATIENT_NAME_EXTRA, name)
                putString(PATIENT_EMAIL_EXTRA, email)
                putString(PATIENT_IMAGE_EXTRA, image)
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            mPatientId = it.getString(PATIENT_ID_EXTRA) ?: ""
            mPatientName = it.getString(PATIENT_NAME_EXTRA) ?: ""
            mPatientEmail = it.getString(PATIENT_EMAIL_EXTRA) ?: ""
            mPatientImage = it.getString(PATIENT_IMAGE_EXTRA) ?: ""
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_phone_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpListenerAndUi()

    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(RegisterPhonePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListenerAndUi(){

        btnGetOtp.setOnClickListener {
            val phone = etPhoneNumber?.text?.toString()
            if (phone!=null && phone.length>=8 && phone.startsWith("09")){
                mPresenter.onTapGetOTP(phone)
            }
            else{
                showErrorMessage("Please enter your phone number correctly")
            }
        }

        btnNext.setOnClickListener {
            val phone = etPhoneNumber?.text?.toString()
            val otp = etOtpCode?.text?.toString()

            if (phone!=null && otp!=null && phone.length>=8 && phone.startsWith("09")){
                mPresenter.onTapNext(mPatientId, mPatientName, mPatientEmail, mPatientImage, phone)
            }
            else{
                showErrorMessage("Please enter your phone and otp code")
            }
        }

    }

    override fun requestOTP(phone: String) {
        Log.d("OTP", "requesting otp...")
    }

    override fun navigateToPasswordScreen(id: String, name: String, email: String, image: String, phone: String) {
        openFragment(RegisterPasswordFragment.newInstance(id, name, email, image, phone))
    }

    fun openFragment(fragment : Fragment){
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }





}