package com.example.revolut.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.revolut.data.dto.CurrencyResponse
import com.example.revolut.data.source.remote.CurrencyRemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CurrencyRepository private constructor(remoteDataSource: CurrencyRemoteDataSource) {

    companion object {

        @Volatile
        private var INSTANCE: CurrencyRepository? = null

        /**
         * Get instance
         * @param remoteDataSource a remote data source implementation
         */
        fun getInstance(remoteDataSource: CurrencyRemoteDataSource): CurrencyRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: CurrencyRepository(remoteDataSource)
                    .also { INSTANCE = it }
            }

    }

    private val compositeDisposable = CompositeDisposable()

    private val _currencies = object : MutableLiveData<CurrencyResponse>() {
        override fun onActive() {
            super.onActive()
            compositeDisposable.add(currenciesDisposable)
        }

        override fun onInactive() {
            super.onInactive()
            compositeDisposable.dispose()
            compositeDisposable.clear()
        }
    }

    val currencies: LiveData<CurrencyResponse>
        get() = _currencies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    /**
     * Request currencies
     */
    private val currenciesDisposable : DisposableObserver<CurrencyResponse> =
        remoteDataSource.getCurrencies()
            .subscribeOn(Schedulers.io())
            .repeatWhen { it.delay(1, TimeUnit.SECONDS) }
            .retry()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<CurrencyResponse>() {
                override fun onComplete() {
                    //nothing to do
                }

                override fun onNext(t: CurrencyResponse) {
                    _currencies.postValue(t)
                }

                override fun onError(e: Throwable) {
                   _error.postValue(e.message ?: "unknown error")
                }
            })
}