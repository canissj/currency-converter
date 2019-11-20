package com.example.revolut.data.source.remote

import com.example.revolut.data.dto.CurrencyResponse
import com.example.revolut.data.source.CurrencyDataSource
import com.example.revolut.data.source.CurrencyService
import io.reactivex.Observable

class CurrencyRemoteDataSource(private val service: CurrencyService) : CurrencyDataSource {

    override fun getCurrencies(): Observable<CurrencyResponse> = service.getCurrencies()

}