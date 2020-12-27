package com.example.thecarefordoctor.view.viewholders

import android.view.View
import com.example.shared.data.vos.MedicationVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecarefordoctor.delegates.MedicationItemDelegate
import kotlinx.android.synthetic.main.item_medicine.view.*

class MedicineViewHolder(itemView: View, private val mDelegate: MedicationItemDelegate) : BaseViewHolder<MedicationVO>(itemView) {


    override fun bindData(data: MedicationVO) {
        itemView.tvMedicine.text = data.medicine

        itemView.btnAdd.setOnClickListener {
            itemView.btnRemove.visibility = View.VISIBLE
            itemView.btnAdd.visibility = View.GONE
            mDelegate.onTapAddMedicine(data)
        }

        itemView.btnRemove.setOnClickListener {
            itemView.btnAdd.visibility = View.VISIBLE
            itemView.btnRemove.visibility = View.GONE
            mDelegate.onTapRemoveMedicine(data)
        }
    }
}