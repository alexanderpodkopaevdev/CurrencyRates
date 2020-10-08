package com.alexanderpodkopaev.currencyrates.domain.repository

import android.content.Context
import com.alexanderpodkopaev.currencyrates.domain.models.CurrencyModel

interface CurrenciesRepository {
    suspend fun fetchCurrencies(context: Context, needOnline: Boolean): List<CurrencyModel>
}