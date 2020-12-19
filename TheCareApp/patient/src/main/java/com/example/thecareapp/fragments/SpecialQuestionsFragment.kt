package com.example.thecareapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.BaseFragment
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.thecareapp.R
import com.example.thecareapp.adapters.SpecialQuestionsAdapter
import com.example.thecareapp.mvp.presenters.SpecialQuestionsPresenter
import com.example.thecareapp.mvp.presenters.impls.SpecialQuestionsPresenterImpl
import com.example.thecareapp.mvp.views.SpecialQuestionsView
import kotlinx.android.synthetic.main.fragment_special_questions.*

class SpecialQuestionsFragment : BaseFragment(), SpecialQuestionsView {

    private lateinit var mPresenter : SpecialQuestionsPresenter
    private lateinit var mAdapter : SpecialQuestionsAdapter
    private lateinit var mContext : Context

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
        mPresenter.onUiReady(this)
        setUpListener()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(SpecialQuestionsPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerview(){
        mAdapter = SpecialQuestionsAdapter(mPresenter)
        rvSpecialQuestions.adapter = mAdapter

        rvSpecialQuestions.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener(){
       // val specialCaseSummary = arrayListOf<CaseSummaryVO>()
        btnContinue.setOnClickListener {
//            for (i in 0..mAdapter.itemCount-1){
//                specialCaseSummary.add(mAdapter.getSpecialCaseSummary())
//            }
            mPresenter.onTapContinue()
        }
    }

    private fun openFragment(fragment : Fragment){
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(R.id.flQuestionsContainer, fragment).commit()
        }
    }

    override fun displaySpecialQuestions(questions: List<SpecialQuestionVO>) {

        mAdapter.setNewData(questions)
    }

    override fun navigateToPatientInfoConfirmation() {
        openFragment(PatientInfoConfirmFragment.newInstance())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}