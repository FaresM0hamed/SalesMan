package com.example.salesman.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
//
//@Entity(foreignKeys = arrayOf(ForeignKey(
//    entity = Shops::class, parentColumns = arrayOf("shopId"), childColumns = arrayOf("shop_id"),
//    onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE,
//)))
@Entity(tableName = "maintenance_table")
class Maintenance(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="maintenance_id") val _id:Int,
    @ColumnInfo(name="productName") val productName:String,
    @ColumnInfo(name="shopName") val shopName:String,
    @ColumnInfo(name="maintenanceType") var maintenanceType:String,
    @ColumnInfo(name="maintenanceDes") var maintenanceDes:String,
    @ColumnInfo(name="isFixed") var isFixed:Boolean,


        )