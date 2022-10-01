package com.example.salesman.model

import androidx.room.*
//import com.example.salesman.data.utils.Converters


////@TypeConverters(Converters::class)
@Entity(tableName = "orders_table",)
class Orders(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="orderId") val orderId:Int,
    @ColumnInfo(name="quantity") val quantity:Int,
    @ColumnInfo(name="shop_name") val shop_name:String,
//    @ColumnInfo(name="date") val date: String?,
    @ColumnInfo(name="productName") var product_name: String)




////sales operation
//@Entity(foreignKeys = arrayOf(ForeignKey(entity =products::class, parentColumns = arrayOf("id"),
//  childColumns = arrayOf("productid"), onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE))),
//
//(foreignKeys = arrayOf(ForeignKey(entity =shop::class, parentColumns = arrayOf("id"),
//    childColumns = arrayOf("shopid"), onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE))),

