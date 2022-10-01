package com.example.salesman.model

import android.provider.ContactsContract
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shops_table")
class Shops(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shopId")
    val shopId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "phone")
    var phone: String,
    @ColumnInfo(name = "date")
    var date:String,
    @ColumnInfo(name = "lat")
    val lat: String,
    @ColumnInfo(name = "lng")
    val lng: String,

)