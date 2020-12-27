package com.example.thecarefordoctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.activities.BaseActivity
import com.example.shared.data.vos.MedicationVO
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.adapters.MedicineAdapter
import com.example.thecarefordoctor.adapters.QuestionAdapter
import com.example.thecarefordoctor.dialogs.MedicationDialog
import com.example.thecarefordoctor.dialogs.PrescriptionDialog
import com.example.thecarefordoctor.mvp.presenters.PrescriptionPresenter
import com.example.thecarefordoctor.mvp.presenters.QuestionsPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.PrescriptionPresenterImpl
import com.example.thecarefordoctor.mvp.views.PrescriptionView
import kotlinx.android.synthetic.main.activity_prescription.*

class PrescriptionActivity : BaseActivity(), PrescriptionView {

    private lateinit var mPresenter : PrescriptionPresenter
    private lateinit var mAdapter : MedicineAdapter
    private lateinit var mConsultationId: String

    companion object{
        const val CONSULTATION_ID_EXTRA = "CONSULTATION_ID_EXTRA"
        fun newIntent(consultationId: String, context: Context): Intent {
            val intent = Intent(context, PrescriptionActivity::class.java)
            intent.putExtra(CONSULTATION_ID_EXTRA, consultationId)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescription)

        mConsultationId = intent.getStringExtra(CONSULTATION_ID_EXTRA)?:"0wBBPLG4o8kyx2xpEHqP"
        setUpPresenter()
        setUpRecyclerview()
        mPresenter.onUiReady(mConsultationId, this)
        setUpListener()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(PrescriptionPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerview(){
        mAdapter = MedicineAdapter(mPresenter)
        rvMedicines.adapter = mAdapter
        rvMedicines.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener(){
        btnFinish.setOnClickListener {
            mPresenter.onTapFinish()
        }
    }

    override fun displayMedicines(medicines: List<MedicationVO>) {
        mAdapter.setNewData(medicines)
    }

    override fun displayMedicationDialog(medicine: String, price: Int) {
        MedicationDialog.newDialog(medicine, price).show(supportFragmentManager, MedicationDialog.MEDICATION_DIALOG)
    }

    override fun navigateToChatScreen() {
        this.finish()
    }


}