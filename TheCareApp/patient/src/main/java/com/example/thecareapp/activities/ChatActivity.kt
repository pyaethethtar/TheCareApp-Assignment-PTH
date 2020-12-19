package com.example.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thecareapp.R

class ChatActivity : AppCompatActivity() {

    companion object{
        fun newIntent(context: Context) : Intent {
            return Intent(context, ChatActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }
}