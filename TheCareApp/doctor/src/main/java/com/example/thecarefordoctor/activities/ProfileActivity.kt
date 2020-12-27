package com.example.thecarefordoctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import com.example.shared.activities.BaseActivity
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.fragments.ProfileEditFragment
import com.example.thecarefordoctor.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity() {

    private lateinit var mDoctorId : String

    companion object{
        const val DOCTOR_ID_EXTRA = "DOCTOR_ID_EXTRA"
        fun newIntent(doctorId: String, context: Context): Intent{
            val intent =  Intent(context, ProfileActivity::class.java)
            intent.putExtra(DOCTOR_ID_EXTRA, doctorId)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        mDoctorId = intent.getStringExtra(DOCTOR_ID_EXTRA)?:"mfw0pfd21nWQgu8o3mWDZKMJFIw1"

//        val ivBackButton = appbar.findViewById<AppCompatImageButton>(R.id.btnBack)
//        ivBackButton.setOnClickListener {
//            Log.d("TheCareMM", "onTapBack")
//        }
//
//        val ivEditButton = appbar.findViewById<AppCompatImageButton>(R.id.btnEdit)
//        ivEditButton.setOnClickListener {
//            Log.d("TheCareMM", "onTapEdit")
//        }

        openFragment(ProfileFragment.newInstance(mDoctorId))
    }

    private fun openFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
    }
}