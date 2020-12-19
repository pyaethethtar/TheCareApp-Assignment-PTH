package com.example.thecareapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shared.adapters.BaseAdapter
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecareapp.R
import com.example.thecareapp.view.viewholders.SpecialQuestionViewHolder

class SpecialQuestionAdapter : BaseAdapter<BaseViewHolder<SpecialQuestionVO>, SpecialQuestionVO>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<SpecialQuestionVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_special_question, parent, false)
        return SpecialQuestionViewHolder(view)
    }
}