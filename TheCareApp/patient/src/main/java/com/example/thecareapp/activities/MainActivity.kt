package com.example.thecareapp.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.shared.BaseActivity
import com.example.thecareapp.R
import com.example.thecareapp.fragments.ConsultationsFragment
import com.example.thecareapp.fragments.HomeFragment
import com.example.thecareapp.fragments.ProfileFragment
import com.example.thecareapp.mvp.presenters.MainPresenter
import com.example.thecareapp.mvp.presenters.impls.MainPresenterImpl
import com.example.thecareapp.mvp.views.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {

    private var mPatientId : String = ""
    private lateinit var mPresenter : MainPresenter

    companion object{
        const val PATIENT_ID_EXTRA = "PATIENT_ID_EXTRA"
        fun newIntent(id: String, context: Context) : Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(PATIENT_ID_EXTRA, id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            return intent
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPatientId = intent.getStringExtra(PATIENT_ID_EXTRA)?:""
        setUpPresenter()
        setUpListener()
        mPresenter.onUiReady(mPatientId, this)

    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(MainPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item_home -> {
                    mPresenter.onTapHome()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.item_consultation -> {
                    mPresenter.onTapConsultationList()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.item_profile -> {
                    mPresenter.onTapProfile()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    fun openFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
    }

    override fun navigateToHomeScreen() {
        openFragment(HomeFragment.newInstance(mPatientId))
    }

    override fun navigateToConsultationScreen() {
        openFragment(ConsultationsFragment())
    }

    override fun navigateToProfileScreen() {
        openFragment(ProfileFragment())
    }


}