package com.example.quotewars.inspect

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotewars.database.QuoteDatabaseDao
import com.example.quotewars.model.QuoteModel
import kotlinx.coroutines.launch

class QuoteInspectViewModel(dataSource: QuoteDatabaseDao, application: Application
):ViewModel() {
    val database = dataSource
    val quote = MutableLiveData<QuoteModel>()

    fun deleteQuote() {
        viewModelScope.launch {
            database.delete(quote.value!!)
        }
    }
    fun getQuote(id: Int){
        viewModelScope.launch {
            quote.value = database.get(id)
        }
    }
}