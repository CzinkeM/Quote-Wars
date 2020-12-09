package com.example.quotewars.inspect

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quotewars.database.QuoteDatabaseDao
import com.example.quotewars.quote.QuoteViewModel

class QuoteInspectViewModelFactory (
    private val dataSource: QuoteDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteInspectViewModel::class.java)) {
            return QuoteInspectViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}