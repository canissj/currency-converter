package com.example.revolut.util

import com.example.revolut.model.CurrencyModel

class CurrencyUtils {
    companion object {

        fun getAmountByRate(amountReference: Float, rate: Float, referenceRate: Float): String =
            ((rate / referenceRate) * amountReference).formatToAmount()

        fun buildCurrencies(rawCurrencies: Map<String, Float>) : List<CurrencyModel> {
            return rawCurrencies.map {
                when (it.key) {
                    "AUD" -> CurrencyModel(id = it.key, name = "Australian Dollar",  flagUrl = "https://www.countryflags.io/au/shiny/64.png", rate = it.value)
                    "BGN" -> CurrencyModel(id = it.key, name = "Bulgarian Lev",  flagUrl = "https://www.countryflags.io/bg/shiny/64.png", rate = it.value)
                    "BRL" -> CurrencyModel(id = it.key, name = "Brazilian Real",  flagUrl = "https://www.countryflags.io/br/shiny/64.png", rate = it.value)
                    "CAD" -> CurrencyModel(id = it.key, name = "Canadian Dollar",  flagUrl = "https://www.countryflags.io/ca/shiny/64.png", rate = it.value)
                    "CHF" -> CurrencyModel(id = it.key, name = "Swiss Franc",  flagUrl = "https://www.countryflags.io/se/shiny/64.png", rate = it.value)
                    "CNY" -> CurrencyModel(id = it.key, name = "Chinese Yuan",  flagUrl = "https://www.countryflags.io/cn/shiny/64.png", rate = it.value)
                    "CZK" -> CurrencyModel(id = it.key, name = "Czech Koruna",  flagUrl = "https://www.countryflags.io/cz/shiny/64.png", rate = it.value)
                    "DKK" -> CurrencyModel(id = it.key, name = "Danish Krone",  flagUrl = "https://www.countryflags.io/dk/shiny/64.png", rate = it.value)
                    "GBP" -> CurrencyModel(id = it.key, name = "Pound Sterling",  flagUrl = "https://www.countryflags.io/gb/shiny/64.png", rate = it.value)
                    "HKD" -> CurrencyModel(id = it.key, name = "Hong Kong Dollar",  flagUrl = "https://www.countryflags.io/hk/shiny/64.png", rate = it.value)
                    "HRK" -> CurrencyModel(id = it.key, name = "Croatian Kuna",  flagUrl = "https://www.countryflags.io/hr/shiny/64.png", rate = it.value)
                    "HUF" -> CurrencyModel(id = it.key, name = "Hunfarian Forint",  flagUrl = "https://www.countryflags.io/hu/shiny/64.png", rate = it.value)
                    "IDR" -> CurrencyModel(id = it.key, name = "Indonesian Rupiah",  flagUrl = "https://www.countryflags.io/id/shiny/64.png", rate = it.value)
                    else -> null
                    // TODO: add all currencies
                }

            }.filterNotNull().toMutableList().also { it.add(0, CurrencyModel(id = "USD", name = "US Dollar", flagUrl = "https://www.countryflags.io/us/shiny/64.png", rate = 1f)) }
        }

    }
}