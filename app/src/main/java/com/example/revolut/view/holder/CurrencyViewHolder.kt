package com.example.revolut.view.holder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.revolut.R
import com.example.revolut.model.CurrencyModel
import com.example.revolut.util.FontConstants
import com.example.revolut.util.loadImage
import com.example.revolut.util.setFont
import com.example.revolut.util.showKeyboard
import com.example.revolut.view.adapter.CurrencyAdapter

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val flagView: ImageView = itemView.findViewById(R.id.currency_flag)
    private val currencyId: TextView = itemView.findViewById(R.id.currency_id)
    private val currencyName: TextView = itemView.findViewById(R.id.currency_name)
    private val currencyAmount: AppCompatEditText = itemView.findViewById(R.id.currency_amount)
    private var flag : String? = null

    @SuppressLint("ClickableViewAccessibility")
    fun onBind(
        currencyModel: CurrencyModel, onAmountListener: CurrencyAdapter.OnAmountChangeListener?,
        onClickListener: CurrencyAdapter.OnClickListener?
    ) {
        currencyModel.run {
            currencyId.text = id
            currencyName.text = name
            currencyAmount.setText(amount)
            flag = flagUrl
            flagView.loadImage(flagUrl)
        }

        currencyName.setFont(FontConstants.ROBOTO_REGULAR)
        currencyId.setFont(FontConstants.ROBOTO_BOLD)

        currencyAmount.run {
            addTextChangedListener(onTextChanged = { text, _, _, _ ->
                if (adapterPosition == 0) {
                    onAmountListener?.onAmountChange(
                        text.toString(), currencyModel.id
                    )
                    setSelection(text?.length ?: 0)
                }
            })
            setOnTouchListener { _, m ->
                itemView.onTouchEvent(m)
            }
            setFont(FontConstants.ROBOTO_BOLD)
        }

        itemView.setOnClickListener {
            if (adapterPosition != 0) {
                onClickListener?.onClick(
                    position = adapterPosition
                )
            }
            currencyAmount.run {
                requestFocus()
                showKeyboard()
                setSelection(text?.length ?: 0)
            }
        }

    }

    fun updateAmount(amount: String) = currencyAmount.setText(amount)

    fun updateFlag() {
        flag?.let {
            flagView.loadImage(it)
        }
        flagView.invalidate()
    }
}