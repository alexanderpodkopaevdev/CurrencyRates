package com.alexanderpodkopaev.currencyrates.domain.repository

import android.content.Context
import android.util.Log
import com.alexanderpodkopaev.currencyrates.data.local.CurrencyDatabase
import com.alexanderpodkopaev.currencyrates.domain.models.CurrencyModel
import com.alexanderpodkopaev.currencyrates.domain.models.currencyMapToModel
import java.net.UnknownHostException

class CurrenciesRepositoryImpl : CurrenciesRepository {


    override suspend fun fetchCurrencies(
        context: Context,
        needOnline: Boolean
    ): List<CurrencyModel> {
        val db = CurrencyDatabase.getInstance(context)
        val currencies = mutableListOf<CurrencyModel>()
        if (!needOnline) {
            Log.d("TAG", "From DB")
            currencies.addAll(
                db.currencyDao().getAll().map {
                    it.currencyMapToModel()
                })
            if (currencies.isNotEmpty()) return currencies
        }

        Log.d("TAG", "From server")
        try {
            currencies.addAll(ResponseRepositoryImpl().fetchCurrencies().currencies.values.map {
                db.currencyDao().insert(it)
                it.currencyMapToModel()
            })
        } catch (e: UnknownHostException) {
            Log.d("TAG", "fetchCurrencies ERROR:  $e")
        }
        return currencies

    }

}
