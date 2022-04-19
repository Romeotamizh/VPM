package com.optisol.vpm.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.optisol.vpm.data.model.Feed

@Database(entities = [Feed::class], version = 1, exportSchema = false)
abstract class FeedDatabase : RoomDatabase() {

    abstract fun feedDao() : FeedDao

    companion object {
        private const val DATABASE_NAME = "feed_database"

        @Volatile
        private var INSTANCE: FeedDatabase? = null

        fun getDatabase(context: Context)  :  FeedDatabase = INSTANCE ?: synchronized(this) {
            val instance =
                Room.databaseBuilder(context, FeedDatabase::class.java, DATABASE_NAME).build()
            INSTANCE = instance
            return@synchronized instance
        }
    }
}