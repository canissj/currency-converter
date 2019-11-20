package com.example.revolut.data.source

import com.example.revolut.data.dto.CurrencyResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("latest?base=USD")
    fun getCurrencies() : Observable<CurrencyResponse>

}