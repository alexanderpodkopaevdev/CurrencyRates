package com.alexanderpodkopaev.currencyrates.data.local

import androidx.room.*
import com.alexanderpodkopaev.currencyrates.data.models.CurrencyRemote

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM CurrencyRemote")
    fun getAll() : List<CurrencyRemote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyRemote: CurrencyRemote)

    @Update
    fun update(currencyRemote: CurrencyRemote)

    @Delete
    fun delete(currencyRemote: CurrencyRemote)
}