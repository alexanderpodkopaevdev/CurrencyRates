package com.alexanderpodkopaev.currencyrates.ui.scenes.currencies.adapter

import com.alexanderpodkopaev.currencyrates.domain.models.CurrencyModel
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyCellModel(
    val charCode: String,
    val name: String,
    val nominal: Int,
    val value: Double
)

fun CurrencyModel.mapToUi(): CurrencyCellModel {
    return CurrencyCellModel(
        charCode = this.charCode,
        name = this.name,
        nominal = this.nominal,
        value = this.value
    )
}