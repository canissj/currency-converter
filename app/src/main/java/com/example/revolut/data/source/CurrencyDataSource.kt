package com.example.revolut.data.source

import com.example.revolut.data.dto.CurrencyResponse
import io.reactivex.Observable

interface CurrencyDataSource {
    fun getCurrencies(): Observable<CurrencyResponse>
}