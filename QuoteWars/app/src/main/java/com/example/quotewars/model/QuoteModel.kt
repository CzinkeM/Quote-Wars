package com.example.quotewars.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "favorite_quote_table")
data class QuoteModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "quote")
    @Json(name = "starWarsQuote")val quote: String,
    @ColumnInfo(name = "faction")
    val faction: Int

)