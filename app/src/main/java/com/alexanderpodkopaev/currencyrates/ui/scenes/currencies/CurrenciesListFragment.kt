package com.alexanderpodkopaev.currencyrates.ui.scenes.currencies

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alexanderpodkopaev.currencyrates.R
import com.alexanderpodkopaev.currencyrates.ui.scenes.currencies.adapter.CurrenciesAdapter
import com.alexanderpodkopaev.currencyrates.ui.scenes.currencies.adapter.CurrencyCellModel
import com.alexanderpodkopaev.currencyrates.ui.scenes.currency.CurrencyFragment
import kotlinx.android.synthetic.main.fragment_currencies_list.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CurrenciesListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
    CurrenciesAdapter.MyOnClickListener {

    private lateinit var currenciesViewModel: CurrenciesViewModel
    private val viewAdapter = CurrenciesAdapter(this)
    private var spref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currenciesViewModel = ViewModelProvider(this).get(CurrenciesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_currencies_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spref = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)

        recyclerViewCurrencies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewCurrencies.adapter = viewAdapter
        setupData()
        setupError()
        setupLoading()
        currenciesViewModel.fetchCurrencies(loadLocalData())
        swipe_container.setOnRefreshListener(this)

    }

    private fun setupError() {
        currenciesViewModel.isError.observe(viewLifecycleOwner, {
            if (it == true) Toast.makeText(
                context,
                "Проверьте подключение интернета",
                Toast.LENGTH_LONG
            ).show();
        })
    }

    private fun setupLoading() {
        currenciesViewModel.isLoading.observe(viewLifecycleOwner, {
            recyclerViewCurrencies.visibility = if (it) View.GONE else View.VISIBLE
            swipe_container.isRefreshing = it
        })
    }

    private fun setupData() {
        currenciesViewModel.currencies.observe(viewLifecycleOwner, {
            spref?.edit()?.putString(CURRENCIES, Json.encodeToString(it))?.apply()
            viewAdapter.setData(it)
        })
    }

    override fun onRefresh() {
        currenciesViewModel.fetchCurrencies("")
    }

    private fun loadLocalData(): String {
        return spref?.getString(CURRENCIES, "") ?: ""

    }

    override fun onClick(currency: CurrencyCellModel) {
        val fragment: Fragment = CurrencyFragment()
        val args = Bundle()
        args.putString(CURRENCY, Json.encodeToString(currency))
        fragment.arguments = args
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment, null)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val CURRENCY: String = "currency"
        const val CURRENCIES: String = "currencies"

    }

}