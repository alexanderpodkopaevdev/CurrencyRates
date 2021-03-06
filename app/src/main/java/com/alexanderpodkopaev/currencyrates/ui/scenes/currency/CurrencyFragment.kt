package com.alexanderpodkopaev.currencyrates.ui.scenes.currency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.alexanderpodkopaev.currencyrates.R
import kotlinx.android.synthetic.main.currency_fragment.*

class CurrencyFragment : Fragment() {

    private val viewModel: CurrencyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currency_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureLayout()

        etSumRub.setText(viewModel.countRub.value.toString())
        etSumRub.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) viewModel.count(s.toString().toDouble())
            }
        })
    }

    private fun configureLayout() {
        viewModel.charCode.observe(viewLifecycleOwner, {
            tvCharCode.text = it
        })

        viewModel.name.observe(viewLifecycleOwner, {
            tvName.text = it
        })
        viewModel.nominal.observe(viewLifecycleOwner, {
            tvValue.text =
                String.format(
                    resources.getString(R.string.currency_rate),
                    viewModel.nominal.value,
                    viewModel.charCode.value,
                    viewModel.values.value
                )
        })
        viewModel.values.observe(viewLifecycleOwner, {
            tvValue.text =
                String.format(
                    resources.getString(R.string.currency_rate),
                    viewModel.nominal.value,
                    viewModel.charCode.value,
                    viewModel.values.value
                )
        })

        viewModel.countRub.observe(viewLifecycleOwner, {
            val valueNominal = viewModel.nominal.value ?: 0
            val valueCountRub = it ?: 0.0
            val valueValue = viewModel.values.value ?: 0.0
            val sum = valueCountRub * valueNominal / valueValue
            tvSum.text = String.format(
                resources.getString(R.string.total_sum),
                sum,
                viewModel.charCode.value
            )
        })


    }

}