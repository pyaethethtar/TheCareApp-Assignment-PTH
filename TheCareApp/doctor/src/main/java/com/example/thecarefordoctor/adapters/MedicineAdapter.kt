package com.example.thecarefordoctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shared.adapters.BaseAdapter
import com.example.shared.data.vos.MedicationVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.delegates.MedicationItemDelegate
import com.example.thecarefordoctor.view.viewholders.MedicineViewHolder

class MedicineAdapter(private val mDelegate: MedicationItemDelegate) : BaseAdapter<BaseViewHolder<MedicationVO>, MedicationVO>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MedicationVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medicine, parent, false)
        return MedicineViewHolder(view, mDelegate)
    }
}