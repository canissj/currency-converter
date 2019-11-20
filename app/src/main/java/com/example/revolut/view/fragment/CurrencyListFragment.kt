package com.example.revolut.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.revolut.R
import com.example.revolut.util.FontConstants
import com.example.revolut.util.setFont
import com.example.revolut.view.adapter.CurrencyAdapter
import com.example.revolut.viewmodel.CurrencyViewModel
import com.example.revolut.viewmodel.MainViewModelFactory

class CurrencyListFragment : Fragment(), CurrencyAdapter.OnAmountChangeListener, CurrencyAdapter.OnClickListener {

    private lateinit var currencyViewModel : CurrencyViewModel
    private lateinit var currencyAdapter: CurrencyAdapter

    companion object {

        /**
         * Create a new instance of the fragment
         */
        fun newInstance() : CurrencyListFragment {
            return CurrencyListFragment()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currency_list_fragment, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyAdapter = CurrencyAdapter(this, this)
        view.findViewById<RecyclerView>(R.id.currency_list_fragment_recycler_view).run {
            adapter = currencyAdapter
            setHasFixedSize(true)
        }

        view.findViewById<TextView>(R.id.title).setFont(FontConstants.ROBOTO_BOLD)

        currencyViewModel =
            ViewModelProviders.of(this, MainViewModelFactory()).get(CurrencyViewModel::class.java)
                .apply {
                    currencies.observe(
                        viewLifecycleOwner,
                        Observer {
                            currencyAdapter.setCurrencies(it)
                        })
                    error.observe(viewLifecycleOwner,
                        Observer {
                            Toast.makeText(context, "Could not update currencies", Toast.LENGTH_LONG).show()
                            // track error message
                        })
                }
    }

    override fun onAmountChange(currencyAmount: String, id: String) {
        currencyViewModel.updateAmount(currencyAmount)
    }

    override fun onClick(position: Int) {
        currencyViewModel.updateCurrency(position)
        currencyAdapter.swapItems(position, 0)
    }

}