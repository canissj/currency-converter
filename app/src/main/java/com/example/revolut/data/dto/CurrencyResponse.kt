package com.example.revolut.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyResponse(val rates: Map<String, Float>) : Parcelable