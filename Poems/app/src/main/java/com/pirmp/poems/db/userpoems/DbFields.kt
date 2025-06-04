package com.pirmp.poems.db.userpoems

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "userpoem")
data class DbFields(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "poem") val poem: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "date") val date: Int,
    @ColumnInfo(name = "place") val place: String,
    @ColumnInfo(name = "fav") val fav: Boolean

): Parcelable