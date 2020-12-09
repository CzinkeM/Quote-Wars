package com.example.quotewars.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quotewars.model.QuoteModel

@Dao
interface QuoteDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: QuoteModel)
    @Query("SELECT * from favorite_quote_table WHERE id = :key")
    suspend fun get(key: Int): QuoteModel
    @Query("DELETE FROM favorite_quote_table")
    suspend fun clear()
    @Query("SELECT * FROM favorite_quote_table")
    fun getAllQuotes(): LiveData<List<QuoteModel>>
    @Delete
    suspend fun delete(quote: QuoteModel)
}