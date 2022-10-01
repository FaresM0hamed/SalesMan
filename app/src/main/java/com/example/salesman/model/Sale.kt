package com.example.salesman.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales_table")
class Sale (

    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "shop_name") val shop_name: String?,
    @ColumnInfo(name = "products") val products: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "totalPaid") val totalPaid: Double?





        )