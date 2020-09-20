package com.alexanderpodkopaev.currencyrates.domain.repository

import com.alexanderpodkopaev.currencyrates.data.network.RetrofitFactory
import com.alexanderpodkopaev.currencyrates.domain.models.ResponseModel
import com.alexanderpodkopaev.currencyrates.domain.models.responseMapToModel

class ResponseRepositoryImpl : ResponseRepository {
    override suspend fun fetchCurrencies(): ResponseModel {
        return RetrofitFactory.instance.api.getCurrency().responseMapToModel()

    }
}