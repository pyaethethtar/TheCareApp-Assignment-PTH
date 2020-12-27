package com.example.thecareapp.dialogs

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.data.vos.CheckoutVO
import com.example.thecareapp.R
import com.example.thecareapp.adapters.CheckoutMedicineAdapter
import com.example.thecareapp.mvp.presenters.MedicationCheckoutDialogPresenter
import com.example.thecareapp.mvp.presenters.impls.MedicationCheckoutDialogPresenterImpl
import com.example.thecareapp.mvp.views.MedicationCheckoutDialogView
import kotlinx.android.synthetic.main.dialog_medication_checkout.*

class MedicationCheckoutDialog  :DialogFragment(), MedicationCheckoutDialogView {

    private var mCheckoutId : String = ""
    private lateinit var mPresenter : MedicationCheckoutDialogPresenter
    private lateinit var mAdapter : CheckoutMedicineAdapter
    private lateinit var mContext: Context

    companion object{
        const val MEDICATION_CHECKOUT_DIALOG = "MEDICATION_CHECKOUT_DIALOG"
        const val BUNDLE_CHECKOUT_ID = "BUNDLE_CHECKOUT_ID"
        fun newDialog(checkoutId: String) : MedicationCheckoutDialog{
            val dialog = MedicationCheckoutDialog()
            val bundle = Bundle()
            bundle.putString(BUNDLE_CHECKOUT_ID, checkoutId)
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCheckoutId = arguments?.getString(BUNDLE_CHECKOUT_ID)?:""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_medication_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecyclerview()
        mPresenter.onUiReady(mCheckoutId, this)
        setUpListener()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(MedicationCheckoutDialogPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerview(){
        mAdapter = CheckoutMedicineAdapter()
        rvMedicines.adapter = mAdapter
        rvMedicines.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener(){
        btnPay.setOnClickListener {
            mPresenter.onTapPay()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun displayCheckoutInfo(checkoutVO: CheckoutVO) {
        checkoutVO.prescriptions?.let {
            mAdapter.setNewData(it)
        }
        var subTotal = checkoutVO.totalAmount
        var fee = checkoutVO.deliveryInfo?.fee ?: 0

        tvSubTotal.text = subTotal.toString()
        tvDeliveryFee.text = fee.toString()
        tvTotal.text = (subTotal+fee).toString()
        tvAddress.text = checkoutVO.deliveryInfo?.address
    }

    override fun navigatetoChatScreen() {
        activity?.finish()
    }

    override fun showErrorMessage(message: String) {
        Log.d("TheCareMM", message)
    }

    override fun onResume() {
        super.onResume()

        val window: Window?= dialog!!.window
        val size = Point()

        window?.let {
            val display: Display = it.getWindowManager().getDefaultDisplay()
            display.getSize(size)

            val width: Int = size.x

            it.setLayout((width * 0.96).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
            it.setGravity(Gravity.CENTER)
        }
    }

}