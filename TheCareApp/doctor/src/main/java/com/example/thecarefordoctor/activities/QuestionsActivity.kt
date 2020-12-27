package com.example.thecarefordoctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.activities.BaseActivity
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.adapters.QuestionAdapter
import com.example.thecarefordoctor.mvp.presenters.QuestionsPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.QuestionsPresenterImpl
import com.example.thecarefordoctor.mvp.views.ChatView
import com.example.thecarefordoctor.mvp.views.QuestionsView
import kotlinx.android.synthetic.main.activity_questions.*

class QuestionsActivity : BaseActivity(), QuestionsView{

    private lateinit var mPresenter : QuestionsPresenter
    private lateinit var mAdapter : QuestionAdapter
    private lateinit var mConsultationId: String

    companion object{
        const val CONSULTATION_ID_EXTRA = "CONSULTATION_ID_EXTRA"
        fun newIntent(consultationId: String, context: Context): Intent{
            val intent = Intent(context, QuestionsActivity::class.java)
            intent.putExtra(CONSULTATION_ID_EXTRA, consultationId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        mConsultationId = intent.getStringExtra(CONSULTATION_ID_EXTRA)?:"0wBBPLG4o8kyx2xpEHqP"
        setUpPresenter()
        setUpRecyclerview()
        mPresenter.onUiReady(mConsultationId, this)
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(QuestionsPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerview(){
        mAdapter = QuestionAdapter(mPresenter)
        rvQuestions.adapter = mAdapter
        rvQuestions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun displayQuestions(questions: List<String>) {
        mAdapter.setNewData(questions)
    }

    override fun navigateToChatScreen(question: String) {
        this.finish()
    }
}