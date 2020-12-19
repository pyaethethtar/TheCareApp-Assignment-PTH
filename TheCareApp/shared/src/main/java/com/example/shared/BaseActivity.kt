package com.example.shared

import androidx.appcompat.app.AppCompatActivity
import com.example.shared.mvp.BaseView
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity(),BaseView {

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_LONG).show()
    }
}