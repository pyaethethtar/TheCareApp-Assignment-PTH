package com.example.thecareapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.shared.BaseFragment
import com.example.shared.utils.EM_CHECK_INTERNET_CONNECTION
import com.example.shared.utils.EM_RETYPE_PASSWORD
import com.example.thecareapp.R
import com.example.thecareapp.activities.MainActivity
import com.example.thecareapp.mvp.presenters.RegisterPasswordPresenter
import com.example.thecareapp.mvp.presenters.impls.RegisterPasswordPresenterImpl
import com.example.thecareapp.mvp.views.RegisterPasswordView
import kotlinx.android.synthetic.main.fragment_register_password.*
import kotlinx.android.synthetic.main.fragment_register_phone_number.*

class RegisterPasswordFragment :BaseFragment(), RegisterPasswordView {

    private lateinit var mContext : Context
    private lateinit var mPresenter : RegisterPasswordPresenter
    private var mPatientId : String = ""
    private var mPatientName : String = ""
    private var mPatientEmail : String = ""
    private var mPatientImage : String = ""
    private var mPatientPhone : String = ""

    companion object{
        const val PATIENT_ID_EXTRA = "PATIENT_ID_EXTRA"
        const val PATIENT_NAME_EXTRA = "PATIENT_NAME_EXTRA"
        const val PATIENT_EMAIL_EXTRA = "PATIENT_EMAIL_EXTRA"
        const val PATIENT_IMAGE_EXTRA = "PATIENT_IMAGE_EXTRA"
        const val PATIENT_PHONE_EXTRA = "PATIENT_PHONE_EXTRA"
        fun newInstance(id: String, name: String, email: String, image: String, phone: String)=RegisterPasswordFragment().apply{
            arguments = Bundle().apply {
                putString(PATIENT_ID_EXTRA, id)
                putString(PATIENT_NAME_EXTRA, name)
                putString(PATIENT_EMAIL_EXTRA, email)
                putString(PATIENT_IMAGE_EXTRA, image)
                putString(PATIENT_PHONE_EXTRA, phone)
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
            mPatientPhone = it.getString(PATIENT_PHONE_EXTRA)?:""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpListener()




    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(RegisterPasswordPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){

        btnConfirm.setOnClickListener {
            val password = etPassword?.text?.toString()
            val repassword = etRetypePassword?.text?.toString()

            if (password!=null && repassword!=null && password==repassword){
                mPresenter.onTapConfirm(mPatientId, mPatientName, mPatientEmail, mPatientImage, mPatientPhone, password)
            }
            else{
                showErrorMessage(EM_RETYPE_PASSWORD)
            }
        }


    }

    override fun navigateToMainScreen() {
        startActivity(MainActivity.newIntent(mPatientId, mContext))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}