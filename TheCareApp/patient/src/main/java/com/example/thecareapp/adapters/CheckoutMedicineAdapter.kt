package com.example.thecareapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shared.adapters.BaseAdapter
import com.example.shared.data.vos.MedicationVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecareapp.R
import com.example.thecareapp.view.viewholders.CheckoutMedicineViewHolder

class CheckoutMedicineAdapter : BaseAdapter<BaseViewHolder<MedicationVO>, MedicationVO>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MedicationVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_checkout_medicine, parent, false)
        return CheckoutMedicineViewHolder(view)
    }
}