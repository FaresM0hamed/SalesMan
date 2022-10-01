package com.example.salesman.data.daos

import androidx.room.*
import com.example.salesman.model.Orders

@Dao
//@TypeConverters(Converters::class)
interface OrderDao {
    @Insert
    suspend fun addOrder(order: Orders)
//    @Update
//    suspend fun updateOrder(order: Orders)
//    @Delete
//    suspend fun deleteOrder(order: Orders)
   @Query("SELECT * from orders_table")
    suspend fun getAllOrders():List<Orders>
}