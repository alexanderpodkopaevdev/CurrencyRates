package com.alexanderpodkopaev.currencyrates.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexanderpodkopaev.currencyrates.data.models.CurrencyRemote

@Database(entities = [CurrencyRemote::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
        private var INSTANCE: CurrencyDatabase? = null
        fun getInstance(context: Context): CurrencyDatabase {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, CurrencyDatabase::class.java, "roomdb").build()
            }
            return INSTANCE as CurrencyDatabase
        }
    }
}