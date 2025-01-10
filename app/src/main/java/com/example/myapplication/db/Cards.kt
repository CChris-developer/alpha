package com.example.myapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Cards(
    @PrimaryKey
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "coordinates")
    val coordinates: String,
    @ColumnInfo(name = "scheme")
    val scheme: String,
    @ColumnInfo(name = "bank")
    val bank: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "city")
    val city: String
)