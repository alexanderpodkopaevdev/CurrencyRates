package com.alexanderpodkopaev.currencyrates.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CurrencyRemote(
    @SerialName("CharCode")
    val charCode: String,
    @SerialName("ID")
    val id: String,
    @SerialName("Name")
    val name: String,
    @SerialName("Nominal")
    val nominal: Int,
    @SerialName("NumCode")
    val numCode: String,
    @SerialName("Previous")
    val previous: Double,
    @SerialName("Value")
    val value: Double
)