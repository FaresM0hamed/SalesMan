package com.example.salesman.data.daos

import androidx.room.*
import com.example.salesman.model.Shops

@Dao

interface ShopDao {

    @Insert
    suspend fun addShop(shop:Shops)

    @Query("SELECT * from shops_table")
    suspend fun getAllShops():List<Shops>

    @Query("UPDATE shops_table SET date=:date WHERE shopId=:shopId")
    suspend fun updateDate(date:String,shopId:Int)

    @Query("DELETE FROM shops_table")
    suspend fun clear()

}