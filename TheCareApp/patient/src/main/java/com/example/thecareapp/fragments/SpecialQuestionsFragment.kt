package com.example.thecareapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.BaseFragment
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.thecareapp.R
import com.example.thecareapp.adapters.SpecialQuestionAdapter
import com.example.thecareapp.dialogs.ConsultationConfirmDialog
import com.example.thecareapp.mvp.presenters.CaseSummaryPresenter
import com.example.thecareapp.mvp.presenters.impls.CaseSummaryPresenterImpl
import kotlinx.android.synthetic.main.fragment_special_questions.*

class SpecialQuestionsFragment : BaseFragment() {

    private lateinit var mPresenter : CaseSummaryPresenter
    private lateinit var mAdapter : SpecialQuestionAdapter

    companion object{
        fun newInstance(): SpecialQuestionsFragment{
            return SpecialQuestionsFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_special_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecyclerview()

    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(CaseSummaryPresenterImpl::class.java)
    }

    private fun setUpRecyclerview(){
        mAdapter = SpecialQuestionAdapter()
        rvSpecialQuestions.adapter = mAdapter
        rvSpecialQuestions.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener(){

        btnContinue.setOnClickListener {

        }
    }

    fun displaySpecialQuestions(questions : List<SpecialQuestionVO>){
        mAdapter.setNewData(questions)
    }
}