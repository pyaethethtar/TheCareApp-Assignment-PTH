package com.example.shared.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProviders
import com.example.shared.data.vos.CaseSummaryVO
import kotlinx.android.synthetic.main.viewpod_patient_info.view.*

class PatientInfoViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var mGeneralCaseSummaryViewpod : GeneralCaseSummaryViewpod
    private lateinit var mSpecialCaseSummaryViewpod: SpecialCaseSummaryViewpod

    override fun onFinishInflate() {
        super.onFinishInflate()

        setUpViewpods()
    }

    private fun setUpViewpods(){
        mGeneralCaseSummaryViewpod = vpGeneralCasesummary as GeneralCaseSummaryViewpod
        mSpecialCaseSummaryViewpod = vpSpecialCaseSummary as SpecialCaseSummaryViewpod
    }

    fun displayGeneralCaseSummary(patientName: String, caseSummary: List<CaseSummaryVO>){
        mGeneralCaseSummaryViewpod.displayPatientInfo(patientName, caseSummary)
    }

    fun displaySpecialCaseSummary(caseSummary: List<CaseSummaryVO>){
        mSpecialCaseSummaryViewpod.displaySpecialCaseSummary(caseSummary)
    }
}