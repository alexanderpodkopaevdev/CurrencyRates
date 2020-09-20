package com.alexanderpodkopaev.currencyrates.domain.repository

import com.alexanderpodkopaev.currencyrates.domain.models.CurrencyModel
import com.alexanderpodkopaev.currencyrates.domain.models.currencyMapToModel

class CurrenciesRepositoryImpl : CurrenciesRepository {


    override suspend fun fetchCurrencies(): List<CurrencyModel> {
        return ResponseRepositoryImpl().fetchCurrencies().currencies.values.map {
            it.currencyMapToModel()
        }
    }


}
