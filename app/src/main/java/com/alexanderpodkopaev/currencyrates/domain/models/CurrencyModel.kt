package com.alexanderpodkopaev.currencyrates.domain.models

import com.alexanderpodkopaev.currencyrates.data.models.CurrencyRemote

data class CurrencyModel(
    val charCode: String,
    val name: String,
    val nominal: Int,
    val previous: Double,
    val value: Double
)

fun CurrencyRemote.currencyMapToModel(): CurrencyModel {
    return CurrencyModel(
        charCode = this.charCode,
        name = this.name,
        nominal = this.nominal,
        previous = this.previous,
        value = this.value
    )
}