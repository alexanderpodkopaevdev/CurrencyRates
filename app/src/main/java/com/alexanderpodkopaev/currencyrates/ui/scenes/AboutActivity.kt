package com.alexanderpodkopaev.currencyrates.ui.scenes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexanderpodkopaev.currencyrates.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}