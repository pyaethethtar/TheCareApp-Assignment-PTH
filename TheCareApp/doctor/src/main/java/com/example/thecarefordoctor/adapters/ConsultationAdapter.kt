package com.example.thecarefordoctor.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shared.adapters.BaseAdapter
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.delegates.ConsultationItemDelegate
import com.example.thecarefordoctor.view.viewholders.ConsultationViewHolder

class ConsultationAdapter(private val mDelegate: ConsultationItemDelegate) : BaseAdapter<BaseViewHolder<ConsultationVO>, ConsultationVO>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ConsultationVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_consultation_history, parent, false)
        return ConsultationViewHolder(view, mDelegate)
    }
}