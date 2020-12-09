package com.example.quotewars.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quotewars.model.QuoteModel

@Database(entities = [QuoteModel::class], version = 1, exportSchema = false)
abstract class QuoteDatabase: RoomDatabase(){
    abstract val quoteDatabaseDao: QuoteDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: QuoteDatabase?=null

        fun getInstance(context: Context): QuoteDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        QuoteDatabase::class.java,
                        "sw_quote_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}