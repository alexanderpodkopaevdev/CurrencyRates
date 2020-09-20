package com.alexanderpodkopaev.currencyrates.ui.scenes.currencies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.currencyrates.domain.repository.CurrenciesRepositoryImpl
import com.alexanderpodkopaev.currencyrates.ui.scenes.currencies.adapter.CurrencyCellModel
import com.alexanderpodkopaev.currencyrates.ui.scenes.currencies.adapter.mapToUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CurrenciesViewModel : ViewModel() {

    private var currenciesRepository = CurrenciesRepositoryImpl()

    private val _currencies = MutableLiveData<List<CurrencyCellModel>>().apply {
        value = ArrayList()
    }
    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }


    val currencies: LiveData<List<CurrencyCellModel>> = _currencies
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchCurrencies(localData: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            withContext(Dispatchers.IO) {
                if (localData.isEmpty()) {
                    Log.d("TAG","From server")
                    val currencies = currenciesRepository.fetchCurrencies()
                    _currencies.postValue(currencies.map {
                        it.mapToUi()
                    })
                } else {
                    Log.d("TAG","From pref")

                    _currencies.postValue(Json.decodeFromString<List<CurrencyCellModel>>(localData))
                }
                _isLoading.postValue(false)


            }
        }
    }

}