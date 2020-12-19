package com.example.shared

import androidx.fragment.app.Fragment
import com.example.shared.mvp.BaseView
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment(), BaseView {

    override fun showErrorMessage(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
        }
    }
}