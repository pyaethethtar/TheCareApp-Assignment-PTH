package com.example.thecareapp.dialogs

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.data.vos.MedicationVO
import com.example.thecareapp.R
import com.example.shared.adapters.PrescriptionAdapter
import com.example.thecareapp.mvp.presenters.PrescriptionDialogPresenter
import com.example.thecareapp.mvp.presenters.impls.PrescriptionDialogPresenterImpl
import com.example.thecareapp.mvp.views.PrescriptionDialogView
import kotlinx.android.synthetic.main.dialog_prescription.*


class PrescriptionDialog : DialogFragment(), PrescriptionDialogView {

    private var mConsultationId: String = ""
    private lateinit var mPresenter : PrescriptionDialogPresenter
    private lateinit var mAdapter : PrescriptionAdapter
    private lateinit var mContext: Context

    companion object{
        const val PRESCRIPTION_DIALOG = "PRESCRIPTION_DIALOG"
        const val BUNDLE_CONSULTATION_ID = "BUNDLE_CONSULTATION_ID"
        fun newDialog(consultationId: String) : PrescriptionDialog{
            val dialog = PrescriptionDialog()
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
        return inflater.inflate(R.layout.dialog_prescription, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecyclerview()
        mPresenter.onUiReady(mConsultationId, this)
        setUpListener()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(PrescriptionDialogPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerview(){
        mAdapter = PrescriptionAdapter()
        rvPrescription.adapter = mAdapter
        rvPrescription.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener(){
        btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun displayPrescriptions(prescriptions: List<MedicationVO>) {
        mAdapter.setNewData(prescriptions)
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

        val window: Window ?= dialog!!.window
        val size = Point()

        window?.let {
            val display: Display = it.getWindowManager().getDefaultDisplay()
            display.getSize(size)

            val width: Int = size.x

            it.setLayout((width * 0.96).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
            it.setGravity(Gravity.CENTER)
        }

    }

}