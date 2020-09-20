package com.alexanderpodkopaev.currencyrates.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseRemote(
    @SerialName("Date")
    val date: String,
    @SerialName("PreviousDate")
    val previousDate: String,
    @SerialName("PreviousURL")
    val previousURL: String,
    @SerialName("Timestamp")
    val timestamp: String,
    @SerialName("Valute")
    val currencies: HashMap<String, CurrencyRemote>
)