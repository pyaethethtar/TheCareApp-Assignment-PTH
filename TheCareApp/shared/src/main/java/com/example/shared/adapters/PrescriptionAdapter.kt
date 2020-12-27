package com.example.shared.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shared.R
import com.example.shared.adapters.BaseAdapter
import com.example.shared.data.vos.MedicationVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.shared.viewholders.PrescriptionViewHolder

class PrescriptionAdapter : BaseAdapter<BaseViewHolder<MedicationVO>, MedicationVO>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MedicationVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prescription, parent, false)
        return PrescriptionViewHolder(view)
    }
}