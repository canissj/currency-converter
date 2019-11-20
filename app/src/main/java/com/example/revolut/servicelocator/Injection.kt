package com.example.revolut.servicelocator

import com.example.revolut.data.repository.CurrencyRepository
import com.example.revolut.data.source.remote.CurrencyRemoteDataSource
import com.example.revolut.networking.RestAdapter

class Injection {
    companion object {
        fun provideCurrencyRepository() = CurrencyRepository.getInstance(CurrencyRemoteDataSource(RestAdapter.getCurrencyService()))
    }
}