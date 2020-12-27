package com.example.thecareapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shared.adapters.BaseAdapter
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecareapp.R
import com.example.thecareapp.mvp.presenters.ConsultationsPresenter
import com.example.thecareapp.view.viewholders.ConsultationViewHolder

class ConsultationAdapter(private val mPresenter: ConsultationsPresenter) : BaseAdapter<BaseViewHolder<ConsultationVO>, ConsultationVO>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ConsultationVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_consultation, parent, false)
        return ConsultationViewHolder(view, mPresenter)
    }
}