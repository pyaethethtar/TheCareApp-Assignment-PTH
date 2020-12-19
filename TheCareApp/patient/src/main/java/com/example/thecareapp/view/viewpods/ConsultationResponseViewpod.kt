package com.example.thecareapp.view.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.shared.data.vos.ConsultationVO
import com.example.thecareapp.delegates.ConsultationResponseDelegate
import kotlinx.android.synthetic.main.viewpod_consultation_response.view.*

class ConsultationResponseViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private lateinit var mDelegate: ConsultationResponseDelegate
    private lateinit var mConsultationId : String

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setDelegate(delegate: ConsultationResponseDelegate){
        mDelegate = delegate
    }

    fun displayConsultationResponse(consultationVO: ConsultationVO){
        mConsultationId = consultationVO.consultationId
        val doctor = consultationVO.doctorInfo
        Glide.with(context).load(doctor.doctorProfileImage).circleCrop().into(ivDoctor)
        tvDoctorName.text = doctor.doctorName
        tvDoctorSpeciality.text = doctor.speciality
        tvDoctorBrief.text = doctor.doctorBrief

        setUpListener()
    }


    private fun setUpListener(){
        btnStartConsultation.setOnClickListener {
            mDelegate.onTapStartConsultation(mConsultationId)
        }
    }
}