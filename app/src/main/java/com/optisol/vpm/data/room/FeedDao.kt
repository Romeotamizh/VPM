package com.optisol.vpm.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.optisol.vpm.data.model.Feed

@Dao
interface FeedDao {
    @Query("SELECT * FROM FEED_TABLE ORDER BY IS_LIVE DESC")
    fun getAllFeeds():List<Feed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeed(feed: Feed)

    @Query("DELETE FROM FEED_TABLE")
    suspend fun deleteAll()


}