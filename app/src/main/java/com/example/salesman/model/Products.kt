package com.example.salesman.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
class Products(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="productId")
    val productId:Int,
    @ColumnInfo(name="productName")
    val productName:String,
    @ColumnInfo(name="price")
    var price:Double,
    @ColumnInfo(name="balance")
    var balance:Int

)