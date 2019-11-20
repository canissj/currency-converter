package com.example.revolut.viewmodel

import androidx.lifecycle.*
import com.example.revolut.data.repository.CurrencyRepository
import com.example.revolut.model.CurrencyModel
import com.example.revolut.util.CurrencyUtils
import com.example.revolut.util.formatToAmount

class CurrencyViewModel(private val repository: CurrencyRepository) : ViewModel() {

    private val currencyList = mutableListOf<CurrencyModel>()

    private var currencyIdReference: String = "USD"
    private var amountReference = 0f
    private var rateReference = 1f
    private var amountReferenceLiveData = MutableLiveData<Float>()

    val error = repository.error

    val currencies = MediatorLiveData<List<CurrencyModel>>().apply {
        addSource(repository.currencies) { source ->
            value = if (currencyList.isEmpty()) {
                CurrencyUtils.buildCurrencies(source.rates).also { currencyList.addAll(it) }
            } else {
                currencyList.also { list ->
                    list.forEach { currency ->
                        source.rates[currency.id]?.let { rate ->
                            if (currency.id != currencyIdReference) {
                                currency.amount = CurrencyUtils.getAmountByRate(amountReference, rate, rateReference)
                            }
                            currency.rate = rate
                        }
                    }
                }
            }
        }
        addSource(amountReferenceLiveData) { amount ->
            value = currencyList.also { list ->
                list.subList(1, list.size)
                    .forEach { item ->
                        item.amount = CurrencyUtils.getAmountByRate(amountReference, item.rate, rateReference)
                    }
            }.also { it[0].amount = amount.formatToAmount() }
        }
    }

    fun updateAmount(amount: String) {
        amountReference = (if (amount.isEmpty()) 0f else amount.toFloat())
            .also { amountReferenceLiveData.postValue(it) }
    }

    fun updateCurrency(position: Int) {
        val elem = currencyList.removeAt(position)
        currencyList.add(0, elem)
        rateReference = elem.rate
        currencyIdReference = elem.id
        amountReference = if (elem.amount.isEmpty()) 0f else elem.amount.toFloat()
    }

}