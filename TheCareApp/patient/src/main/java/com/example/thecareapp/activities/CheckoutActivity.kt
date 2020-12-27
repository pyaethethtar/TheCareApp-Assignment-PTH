package com.example.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.activities.BaseActivity
import com.example.shared.data.vos.CheckoutVO
import com.example.thecareapp.R
import com.example.thecareapp.adapters.CheckoutMedicineAdapter
import com.example.thecareapp.dialogs.MedicationCheckoutDialog
import com.example.thecareapp.mvp.presenters.CheckoutPresenter
import com.example.thecareapp.mvp.presenters.impls.CheckoutPresenterImpl
import com.example.thecareapp.mvp.views.CheckoutView
import com.example.thecareapp.view.viewpods.NewAddressViewpod
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : BaseActivity(), CheckoutView {

    private var mCheckoutId : String = ""
    private lateinit var mPresenter : CheckoutPresenter
    private lateinit var mAdapter : CheckoutMedicineAdapter
    private lateinit var mNewAddressViewpod: NewAddressViewpod

    companion object{
        const val CHECKOUT_ID_EXTRA = "CHECKOUT_ID_EXTRA"
        fun newIntent(checkoutId: String, context: Context): Intent{
            val intent = Intent(context, CheckoutActivity::class.java)
            intent.putExtra(CHECKOUT_ID_EXTRA, checkoutId)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        mCheckoutId = intent.getStringExtra(CHECKOUT_ID_EXTRA) ?: ""
        setUpPresenterAndViewpod()
        setUpRecyclerview()
        mPresenter.onUiReady(mCheckoutId, this)
        setUpListener()
    }

    private fun setUpPresenterAndViewpod(){
        mPresenter = ViewModelProviders.of(this).get(CheckoutPresenterImpl::class.java)
        mPresenter.initPresenter(this)

        mNewAddressViewpod = vpNewAddress as NewAddressViewpod
    }

    private fun setUpRecyclerview(){
        mAdapter = CheckoutMedicineAdapter()
        rvMedicines.adapter = mAdapter
        rvMedicines.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener(){
        val address = mNewAddressViewpod.getAddress()
        btnCheckout.setOnClickListener {
            mPresenter.onTapCheckout(address)
        }
    }

    override fun displayCheckoutInfo(checkout: CheckoutVO) {
        checkout.prescriptions?.let {
            mAdapter.setNewData(it)
        }
        tvTotal.text = checkout.totalAmount.toString()
    }

    override fun displayCheckoutDialog() {
        MedicationCheckoutDialog.newDialog(mCheckoutId)
            .show(supportFragmentManager, MedicationCheckoutDialog.MEDICATION_CHECKOUT_DIALOG)
    }

    override fun displayAddressViewpod() {
        vpAddress.visibility = View.VISIBLE
        vpNewAddress.visibility = View.GONE
    }

    override fun displayNewAddressViewpod() {
        vpNewAddress.visibility = View.VISIBLE
        vpAddress.visibility = View.GONE
    }


}