package com.example.thecarefordoctor.dialogs

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.viewpods.GeneralCaseSummaryViewpod
import com.example.shared.viewpods.SpecialCaseSummaryViewpod
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.mvp.presenters.ConsultationHistoryDialogPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.ConsultationHistoryDialogPresenterImpl
import com.example.thecarefordoctor.mvp.views.ConsultationHistoryDialogView
import kotlinx.android.synthetic.main.dialog_consultation_history.*

class ConsultationHistoryDialog : DialogFragment(), ConsultationHistoryDialogView {

    private lateinit var mContext : Context
    private lateinit var mPresenter : ConsultationHistoryDialogPresenter
    private lateinit var mGeneralCaseSummaryViewpod: GeneralCaseSummaryViewpod
    private lateinit var mSpecialCaseSummaryViewpod: SpecialCaseSummaryViewpod
    private var mConsultationId : String = ""

    companion object{
        const val BUNDLE_CONSULTATION_ID = "BUNDLE_CONSULTATION_ID"
        const val CONSULTATION_HISTORY_DIALOG = "CONSULTATION_HISTORY_DIALOG"
        fun newDialog(consultationId: String) : ConsultationHistoryDialog{
            val dialog = ConsultationHistoryDialog()
            val bundle = Bundle()
            bundle.putString(BUNDLE_CONSULTATION_ID, consultationId)
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mConsultationId = arguments?.getString(BUNDLE_CONSULTATION_ID) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_consultation_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenterAndViewpods()
        mPresenter.onUiReady(mConsultationId, this)
        setUpListener()
    }

    private fun setUpPresenterAndViewpods(){
        mPresenter = ViewModelProviders.of(this).get(ConsultationHistoryDialogPresenterImpl::class.java)
        mPresenter.initPresenter(this)

        mGeneralCaseSummaryViewpod = vpGeneralCasesummary as GeneralCaseSummaryViewpod
        mSpecialCaseSummaryViewpod = vpSpecialCaseSummary as SpecialCaseSummaryViewpod
    }

    private fun setUpListener(){
        btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun displayConsultationHistory(consultationVO: ConsultationVO) {
        mGeneralCaseSummaryViewpod.displayPatientInfo(consultationVO.patientInfo.patientName, consultationVO.caseSummary)
        mGeneralCaseSummaryViewpod.displayConsultationDate(consultationVO.consultationDate)

        mSpecialCaseSummaryViewpod.displaySpecialCaseSummary(consultationVO.caseSummary)
    }

    override fun showErrorMessage(message: String) {
        Log.d("TheCareMM", message)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onResume() {
        super.onResume()

        val window: Window?= dialog!!.window
        val size = Point()

        window?.let {
            val display: Display = it.getWindowManager().getDefaultDisplay()
            display.getSize(size)

            val width: Int = size.x

            it.setLayout((width * 0.90).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
            it.setGravity(Gravity.CENTER)
        }
    }
}