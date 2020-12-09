package com.example.quotewars.favorites

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.quotewars.database.QuoteDatabaseDao
import com.example.quotewars.model.QuoteModel
import kotlinx.coroutines.launch

class FavoritesViewModel(
    dataSource: QuoteDatabaseDao,
    application: Application): ViewModel() {
    val database = dataSource
    val allQuotes = database.getAllQuotes()
    val allFavoriteQuote = database.getAllQuotes()

    fun clearDatabase(){
        viewModelScope.launch {
            clear()
        }
    }
    private suspend fun clear() {
        database.clear()
        Log.i("clear",allQuotes.value.toString())
    }
    fun onClickListItem(id: Int,view: View) {
        viewModelScope.launch {
            val gottenQuote = database.get(id)
            val action = FavoritesFragmentDirections.navigateToQuoteInspectfragment(gottenQuote.quote,id)
            Navigation.findNavController(view).navigate(action)

        }
    }
    //Listához ad hozzá a favoritokhoz adás a diff utillal nézni hogy különbözik-e roomtól kapotttól

}