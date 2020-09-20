package com.alexanderpodkopaev.currencyrates.domain.repository

import com.alexanderpodkopaev.currencyrates.domain.models.CurrencyModel

interface CurrenciesRepository {
    suspend fun fetchCurrencies(): List<CurrencyModel>
}