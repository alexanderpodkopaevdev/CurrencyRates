package com.alexanderpodkopaev.currencyrates.ui.scenes.currencies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.currencyrates.R

class CurrenciesAdapter(private val listener: MyOnClickListener) : RecyclerView.Adapter<CurrenciesAdapter.CurrencyViewHolder>() {
    private val values = ArrayList<CurrencyCellModel>()

    fun setData(newDate: List<CurrencyCellModel>) {
        values.clear()
        values.addAll(newDate)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_item, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(values[position])
        holder.itemView.setOnClickListener {
            listener.onClick(values[position])

        }
    }

    override fun getItemCount(): Int = values.size

    class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvCharCode: TextView = view.findViewById(R.id.tvCharCode)
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvValue: TextView = view.findViewById(R.id.tvValue)



        fun bind(currency: CurrencyCellModel) {
            tvCharCode.text = currency.charCode
            tvName.text = currency.name
            tvValue.text = String.format(itemView.context.resources.getString(R.string.currency_rate),currency.nominal,currency.charCode,currency.value)
        }

    }

    interface MyOnClickListener {
        fun onClick(currency: CurrencyCellModel)
    }
}