package com.example.thecarefordoctor.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shared.adapters.BaseAdapter
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.delegates.ConsultationItemDelegate
import com.example.thecarefordoctor.delegates.RequestItemDelegate
import com.example.thecarefordoctor.view.viewholders.RequestViewHolder

class RequestAdapter(private val mDelegate: RequestItemDelegate) : BaseAdapter<BaseViewHolder<ConsultationRequestVO>, ConsultationRequestVO>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ConsultationRequestVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_consultation_request, parent, false)
        return RequestViewHolder(view, mDelegate)
    }
}