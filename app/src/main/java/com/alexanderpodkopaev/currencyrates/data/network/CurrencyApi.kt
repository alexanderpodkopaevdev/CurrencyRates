package com.alexanderpodkopaev.currencyrates.data.network

import com.alexanderpodkopaev.currencyrates.data.models.ResponseRemote
import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js")
    suspend fun getCurrency(
    ): ResponseRemote
}