package com.example.thecarefordoctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shared.BaseActivity
import com.example.thecarefordoctor.R

class ChatActivity : BaseActivity() {

    companion object{
        fun newIntent(context: Context) : Intent{
            return Intent(context, ChatActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }
}