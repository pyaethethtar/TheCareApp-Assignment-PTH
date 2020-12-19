package com.example.thecareapp.view.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.shared.data.vos.DoctorVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecareapp.delegates.RecentDoctorDelegate
import kotlinx.android.synthetic.main.item_recent_doctor.view.*

class RecentDoctorViewHolder(itemView: View, private val mDelegate: RecentDoctorDelegate) :BaseViewHolder<DoctorVO>(itemView) {

    override fun bindData(data: DoctorVO) {
        Glide.with(itemView.context).load(data.doctorProfileImage).circleCrop().into(itemView.ivRecentDoctor)
        itemView.tvRecentDoctorName.text = data.doctorName
        itemView.tvRecentDoctorSpeciality.text = data.speciality

        itemView.setOnClickListener {
            mDelegate.onTapDoctor(data)
        }
    }
}