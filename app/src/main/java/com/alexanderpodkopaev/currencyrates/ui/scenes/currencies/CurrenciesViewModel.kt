package com.alexanderpodkopaev.currencyrates.ui.scenes.currencies

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.currencyrates.domain.repository.CurrenciesRepositoryImpl
import com.alexanderpodkopaev.currencyrates.ui.scenes.currencies.adapter.CurrencyCellModel
import com.alexanderpodkopaev.currencyrates.ui.scenes.currencies.adapter.mapToUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CurrenciesViewModel(application: Application) : AndroidViewModel(application) {

    private var currenciesRepository = CurrenciesRepositoryImpl()

    private val _currencies = MutableLiveData<List<CurrencyCellModel>>().apply {
        value = ArrayList()
    }
    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }
    private val _isError = MutableLiveData<Boolean>().apply {
        value = false
    }


    val currencies: LiveData<List<CurrencyCellModel>> = _currencies
    val isLoading: LiveData<Boolean> = _isLoading
    val isError: LiveData<Boolean> = _isError

    fun fetchCurrencies(localData: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            withContext(Dispatchers.IO) {
                if (localData.isEmpty()) {
                    if (isOnline()) {
                        _isError.postValue(false)
                        Log.d("TAG", "From server")
                        val currencies = currenciesRepository.fetchCurrencies()
                        _currencies.postValue(currencies.map {
                            it.mapToUi()
                        })
                    } else {
                        _isError.postValue(true)
                    }
                } else {
                    Log.d("TAG", "From pref")

                    _currencies.postValue(Json.decodeFromString<List<CurrencyCellModel>>(localData))
                }
                _isLoading.postValue(false)


            }
        }
    }

    private fun isOnline(): Boolean {
        val cm =
            getApplication<Application>().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as (ConnectivityManager)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = cm.activeNetwork ?: return false
            val actNw = cm.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val nwInfo = cm.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

}