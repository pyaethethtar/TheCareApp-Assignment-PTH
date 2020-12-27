package com.example.thecarefordoctor.dialogs

import android.graphics.Point
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.shared.data.vos.MedicationVO
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.mvp.presenters.PrescriptionPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.PrescriptionPresenterImpl
import kotlinx.android.synthetic.main.dialog_medication.*

class MedicationDialog : DialogFragment() {

    private lateinit var mMedicine : String
    private var mPrice = 0
    private lateinit var mPresenter : PrescriptionPresenter

    companion object{
        const val MEDICATION_DIALOG = "MEDICATION_DIALOG"
        const val BUNDLE_MEDICINE = "BUNDLE_MEDICINE"
        const val BUNDLE_PRICE = "BUNDLE_PRICE"
        fun newDialog(medicine: String, price: Int) : MedicationDialog{
            val dialog = MedicationDialog()
            val bundle = Bundle()
            bundle.putString(BUNDLE_MEDICINE, medicine)
            bundle.putInt(BUNDLE_PRICE, price)
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMedicine = arguments?.getString(BUNDLE_MEDICINE) ?: ""
        mPrice = arguments?.getInt(BUNDLE_PRICE) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_medication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvMedicine.text = mMedicine
        setUpPresenter()
        setUpListener()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(PrescriptionPresenterImpl::class.java)
    }

    private fun setUpListener(){
        var takingTimes = ""
        var takingTime=0

        btnMorning.setOnClickListener {
            btnMorning.setBackgroundResource(R.drawable.rounded_corner_blue)
            takingTimes = "$takingTimes Morning "
            takingTime++
        }

        btnLunch.setOnClickListener {
            btnLunch.setBackgroundResource(R.drawable.rounded_corner_blue)
            takingTimes = "$takingTimes Noon"
            takingTime++
        }

        btnNight.setOnClickListener {
            btnNight.setBackgroundResource(R.drawable.rounded_corner_blue)
            takingTimes = "$takingTimes Evening"
            takingTime++
        }

        btnBefore.setOnClickListener {
            btnBefore.setBackgroundResource(R.drawable.rounded_corner_blue)
        }

        btnAfter.setOnClickListener {
            btnAfter.setBackgroundResource(R.drawable.rounded_corner_blue)
        }


        btnAdd.setOnClickListener {
            val medicationVO = MedicationVO()
            medicationVO.medicine = tvMedicine.text.toString()
            medicationVO.price = mPrice

            var takingDays = etDays.text.toString().toInt()
            when(spinnerDays.selectedItem.toString()){
                "Day" -> takingDays = takingDays
                "Week" -> takingDays = takingDays*7
                "Month" -> takingDays = takingDays*30
            }
            medicationVO.takingDays = takingDays
            medicationVO.takingTimes = takingTimes
            medicationVO.count = etCount.text.toString().toInt() * takingDays * takingTime


            var beforeAfter = ""
            if (btnBefore.background==resources.getDrawable(R.drawable.rounded_corner_blue)){
                beforeAfter = "Before"
            }
            else{
                beforeAfter = "After"
            }
            medicationVO.beforeAfter = beforeAfter
            medicationVO.medicationNote = etNote.text.toString()

            mPresenter.onTapAdd(medicationVO)
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()

        val window: Window?= dialog!!.window
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