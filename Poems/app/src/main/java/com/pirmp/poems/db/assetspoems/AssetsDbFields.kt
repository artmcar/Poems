package com.pirmp.poems.db.assetspoems

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assetspoem")
data class AssetsDbFields(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "poem") val poem: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "date") val date: Int,
    @ColumnInfo(name = "place") val place: String,
    @ColumnInfo(name = "fav") val fav: Boolean

)
