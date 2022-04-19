package com.optisol.vpm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.optisol.vpm.data.model.Feed.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Feed(
    @PrimaryKey var name: String,
    @ColumnInfo(name = "is_live") var isLive: Boolean,
    @ColumnInfo(name = "created_date_time") var createdDateTime: String,
) {
    companion object {
        const val TABLE_NAME = "feed_table"
        const val IS_LIVE = "is_live"
    }
}