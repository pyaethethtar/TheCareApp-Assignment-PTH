package com.example.thecareapp.delegates

import com.example.shared.data.vos.CaseSummaryVO

interface SpecialCaseSummaryDelegate {

    fun onAnswerSpecialQuestion(position: Int, casesummary: CaseSummaryVO)
}