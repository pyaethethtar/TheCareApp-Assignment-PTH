package com.example.thecareapp.view.viewholders

import android.view.View
import com.example.shared.data.vos.MedicationVO
import com.example.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_checkout_medicine.view.*

class CheckoutMedicineViewHolder(itemView: View) : BaseViewHolder<MedicationVO>(itemView) {

    override fun bindData(data: MedicationVO) {
        itemView.tvMedicine.text = data.medicine
        itemView.tvCount.text = data.count.toString()
        data.count?.let {
            itemView.tvSubTotal.text = (it*data.price).toString()
        }
    }
}