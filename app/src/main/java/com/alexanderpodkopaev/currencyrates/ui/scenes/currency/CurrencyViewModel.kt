package com.alexanderpodkopaev.currencyrates.ui.scenes.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexanderpodkopaev.currencyrates.ui.scenes.currencies.adapter.CurrencyCellModel

class CurrencyViewModel : ViewModel() {

    private val _charCode: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    private val _name: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    private val _nominal: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    private val _value: MutableLiveData<Double> = MutableLiveData<Double>().apply { value = 0.0 }
    private val _countRub: MutableLiveData<Double> = MutableLiveData<Double>().apply { value = 0.0 }


    val charCode: LiveData<String> = _charCode
    val name: LiveData<String> = _name
    val nominal: LiveData<Int> = _nominal
    val values: LiveData<Double> = _value
    val countRub: LiveData<Double> = _countRub


    fun fetchData(currency: CurrencyCellModel) {
        _charCode.postValue(currency.charCode)
        _name.postValue(currency.name)
        _nominal.postValue(currency.nominal)
        _value.postValue(currency.value)
    }

    fun count(rub: Double) {
        _countRub.postValue(rub)
    }
}