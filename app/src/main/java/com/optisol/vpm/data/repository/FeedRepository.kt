package com.optisol.vpm.data.repository

import androidx.annotation.WorkerThread
import com.optisol.vpm.data.model.Feed
import com.optisol.vpm.data.room.FeedDao

class FeedRepository(private val feedDao: FeedDao) {

    @Suppress("RedundantSuspendModifier")
    suspend fun getAllFeeds() = feedDao.getAllFeeds()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertFeed(feed: Feed) {
        feedDao.insertFeed(feed)
    }
}