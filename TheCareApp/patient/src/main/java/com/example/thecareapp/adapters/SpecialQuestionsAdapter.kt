package com.example.thecareapp.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shared.adapters.BaseAdapter
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecareapp.R
import com.example.thecareapp.delegates.SpecialCaseSummaryDelegate
import com.example.thecareapp.view.viewholders.SpecialQuestionViewHolder
import kotlinx.android.synthetic.main.item_special_question.view.*

class SpecialQuestionsAdapter(private val mDelegate: SpecialCaseSummaryDelegate) : BaseAdapter<BaseViewHolder<SpecialQuestionVO>, SpecialQuestionVO>() {

    private lateinit var mView : View

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<SpecialQuestionVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_special_question, parent, false)
        mView = view
        return SpecialQuestionViewHolder(view, mDelegate)
    }

    fun getSpecialCaseSummary(): CaseSummaryVO{
        val caseSummaryVO = CaseSummaryVO()
        caseSummaryVO.question = mView.tvSpecialQuestion.text.toString()
        caseSummaryVO.answer = mView.etSpecialAnswer.text.toString()
        return caseSummaryVO
    }
}