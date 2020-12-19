package com.example.thecareapp.view.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.shared.data.vos.SpecialityVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecareapp.delegates.SpecialityItemDelegate
import kotlinx.android.synthetic.main.item_speciality.view.*

class SpecialityViewHolder(itemView: View, private val mDelegate : SpecialityItemDelegate) :  BaseViewHolder<SpecialityVO>(itemView) {

    override fun bindData(data: SpecialityVO) {
        Glide.with(itemView.context).load(data.image).into(itemView.ivSpeciality)
        itemView.tvSpeciality.text = data.speciality

        itemView.setOnClickListener {
            mDelegate.onTapSpeciality(data.speciality)
        }
    }
}