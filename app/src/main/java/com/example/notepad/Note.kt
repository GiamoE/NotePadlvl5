package com.example.notepad

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Note (

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "lastUpdated")
    var lastUpdated: String,

    @ColumnInfo(name = "text")
    var text: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

) : Parcelable