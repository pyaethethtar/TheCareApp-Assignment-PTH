package com.example.thecareapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shared.adapters.BaseAdapter
import com.example.shared.data.vos.SpecialityVO
import com.example.thecareapp.R
import com.example.thecareapp.delegates.SpecialityItemDelegate
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecareapp.view.viewholders.SpecialityViewHolder

class SpecialityAdapter(private val mDelegate : SpecialityItemDelegate) : BaseAdapter<BaseViewHolder<SpecialityVO>, SpecialityVO>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<SpecialityVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_speciality, parent, false)
        return SpecialityViewHolder(view, mDelegate)
    }
}