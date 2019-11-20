package com.example.revolut.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.revolut.R
import com.example.revolut.model.CurrencyModel
import com.example.revolut.view.holder.CurrencyViewHolder
import java.lang.ref.WeakReference

class CurrencyAdapter(amountListener: OnAmountChangeListener, onClickListener: OnClickListener):
    RecyclerView.Adapter<CurrencyViewHolder>() {

    private val currencies: MutableList<CurrencyModel> = mutableListOf()
    private val onAmountListener = WeakReference<OnAmountChangeListener>(amountListener)
    private val onClickListener = WeakReference<OnClickListener>(onClickListener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.currency_view_holder,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.onBind(currencies[position], onAmountListener.get(), onClickListener.get())
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int, payloads: MutableList<Any>) {
        holder.updateFlag()
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val list = payloads[0] as List<String>
            holder.updateAmount(list[position])
        }
    }

    override fun getItemCount(): Int = currencies.size

    fun setCurrencies(listOfCurrencies: List<CurrencyModel>) {
        val firstTime = currencies.isEmpty()

        currencies.run {
            clear()
            addAll(listOfCurrencies)
        }

        if (firstTime)
            notifyDataSetChanged()
        else {
            notifyItemRangeChanged(1, currencies.size - 1, listOfCurrencies.map { it.amount })
        }

    }

    fun swapItems(fromPosition: Int, toPosition: Int) {
        val elem = currencies.removeAt(0)
        currencies.add(0, elem)
        notifyItemMoved(fromPosition, toPosition)
    }

    interface OnAmountChangeListener {
        fun onAmountChange(currencyAmount: String, id: String)
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }
}