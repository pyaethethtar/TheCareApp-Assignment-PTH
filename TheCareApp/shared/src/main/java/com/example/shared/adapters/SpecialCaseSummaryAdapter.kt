package com.example.shared.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shared.R
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.shared.viewholders.SpecialCaseSummaryViewHolder

class SpecialCaseSummaryAdapter : BaseAdapter<BaseViewHolder<CaseSummaryVO>, CaseSummaryVO>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CaseSummaryVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_special_case_summary, parent, false)
        return SpecialCaseSummaryViewHolder(view)
    }
}