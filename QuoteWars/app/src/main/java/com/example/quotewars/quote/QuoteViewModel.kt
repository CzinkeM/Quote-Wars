package com.example.quotewars.quote

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.example.quotewars.database.QuoteDatabaseDao
import com.example.quotewars.model.QuoteModel
import com.example.quotewars.network.SWApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class QuoteViewModel(dataSource: QuoteDatabaseDao, application: Application
): ViewModel() {
    private val _response = MutableLiveData<QuoteModel>()
    private val database = dataSource
    val allQuotes = database.getAllQuotes()


    fun response(): LiveData<QuoteModel> {
        return _response
    }

    init {
        getStarWarsQuote()
    }
    fun addToFavorite(){
        viewModelScope.launch {
            insertQuoteToFavorites(_response.value!!)
        }
    }
    private suspend fun insertQuoteToFavorites(quote: QuoteModel) {
        database.insert(quote)
    }

    fun getStarWarsQuote() {
        viewModelScope.launch() {
            try{
                val result = withContext(Dispatchers.IO){ SWApi.retrofitService.getQuote()}
                Log.i("apiCall","The quote: ${result.faction}")
                _response.value = result
            }catch (e: Exception){
                Log.w("apiCall",e)
            }
        }
    }
}