package com.example.shared.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.example.shared.R
import com.example.shared.data.vos.CaseSummaryVO
import kotlinx.android.synthetic.main.viewpod_patient_info.view.*

class PatientInfoViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {


    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun displayPatientInfo(name: String, caseSummary: List<CaseSummaryVO>){
        tvPatientName.text = name
        for (cs in caseSummary){
            when(cs.question) {
                context.getString(R.string.lbl_dob) -> tvDob.text = cs.answer
                context.getString(R.string.lbl_height) -> tvHeight.text = cs.answer
                context.getString(R.string.lbl_blood_type) -> tvBloodType.text = cs.answer
                context.getString(R.string.lbl_allergic_medicine) -> tvAllergicMedicine.text = cs.answer
                context.getString(R.string.tv_weight) -> tvWeight.text = cs.answer
                context.getString(R.string.tv_blood_pressure) -> tvBloodPressure.text = cs.answer
            }
        }
    }
}