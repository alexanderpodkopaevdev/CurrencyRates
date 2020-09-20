package com.alexanderpodkopaev.currencyrates.domain.models

import com.alexanderpodkopaev.currencyrates.data.models.CurrencyRemote
import com.alexanderpodkopaev.currencyrates.data.models.ResponseRemote

data class ResponseModel(
    val date: String,
    val previousDate: String,
    val previousURL: String,
    val timestamp: String,
    val currencies: HashMap<String, CurrencyRemote>
)

fun ResponseRemote.responseMapToModel(): ResponseModel {
    return ResponseModel(
        date = this.date,
        previousDate = this.previousDate,
        previousURL = this.previousURL,
        timestamp = this.timestamp,
        currencies = this.currencies
    )
}