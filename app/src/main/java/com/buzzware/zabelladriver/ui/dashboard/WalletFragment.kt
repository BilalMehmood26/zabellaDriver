package com.buzzware.zabelladriver.ui.dashboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.buzzware.zabelladriver.R
import com.buzzware.zabelladriver.databinding.FragmentWalletBinding
import com.buzzware.zabelladriver.ui.adapter.CreditCardAdapter
import com.stripe.android.Stripe


class WalletFragment : Fragment() {

    //private var db = Firebase.firestore
    private lateinit var stripe: Stripe
    private lateinit var creditCardAdapter: CreditCardAdapter
    private var secrat = ""
    //private var cardList: ArrayList<PaymentMethodsResponse.PaymentMethod> = ArrayList()

    private val binding: FragmentWalletBinding by lazy {
        FragmentWalletBinding.inflate(layoutInflater)
    }
    private lateinit var fragmentContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateAdapter()
        binding.addCardBtn.setOnClickListener {

        }
        return binding.root
    }

    private fun updateAdapter() {
        binding.cardListRv.layoutManager = LinearLayoutManager(fragmentContext)
        creditCardAdapter = CreditCardAdapter(fragmentContext, arrayListOf()) { pmID, pos ->
            // showAlertDialog(pmID, pos)

        }
        binding.cardListRv.adapter = creditCardAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

}