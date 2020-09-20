package com.alexanderpodkopaev.currencyrates.domain.repository

import com.alexanderpodkopaev.currencyrates.domain.models.ResponseModel

interface ResponseRepository {
    suspend fun fetchCurrencies(): ResponseModel

}