package com.example.shared.viewholders

import android.view.View
import com.example.shared.data.vos.MedicationVO
import kotlinx.android.synthetic.main.item_prescription.view.*

class PrescriptionViewHolder(itemView: View) : BaseViewHolder<MedicationVO>(itemView) {

    override fun bindData(data: MedicationVO) {
        itemView.tvMedicine.text = data.medicine
        itemView.tvAmount.text = "700 mg"
        itemView.tvCount.text = "1 Tablet"
        itemView.tvDays.text = data.takingDays.toString()+" Days"
        itemView.tvTimes.text = data.takingTimes
        itemView.tvRepeat.text = "every day"
        itemView.tvNote.text = data.medicationNote
    }
}