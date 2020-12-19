package com.example.thecareapp.view.viewholders

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecareapp.delegates.SpecialCaseSummaryDelegate
import kotlinx.android.synthetic.main.item_special_question.view.*

class SpecialQuestionViewHolder(itemView: View, private val mDelegate: SpecialCaseSummaryDelegate) : BaseViewHolder<SpecialQuestionVO>(itemView) {

    @SuppressLint("SetTextI18n")
    override fun bindData(data: SpecialQuestionVO) {
        itemView.tvSpecialQuestion.text = data.specialQuestion

        itemView.etSpecialAnswer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                val casesummary= CaseSummaryVO(itemView.tvSpecialQuestion.text.toString(), itemView.etSpecialAnswer.text.toString())
                mDelegate.onAnswerSpecialQuestion(casesummary)
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }


}