package com.example.thecareapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shared.adapters.BaseAdapter
import com.example.shared.data.vos.DoctorVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecareapp.R
import com.example.thecareapp.delegates.RecentDoctorDelegate
import com.example.thecareapp.view.viewholders.RecentDoctorViewHolder

class RecentDoctorAdapter(private val mDelegate: RecentDoctorDelegate) : BaseAdapter<BaseViewHolder<DoctorVO>, DoctorVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DoctorVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recent_doctor, parent, false)
        return RecentDoctorViewHolder(view, mDelegate)
    }
}