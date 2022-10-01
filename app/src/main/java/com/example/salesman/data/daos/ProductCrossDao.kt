package com.example.salesman.data.daos
//
//import androidx.room.Dao
//import androidx.room.Query
//import androidx.room.Transaction
//import com.example.salesman.data.crossRef.OrderWithProducts
//import com.example.salesman.data.crossRef.ProductWithOrders
//import com.example.salesman.data.crossRef.ProductWithShops
//import com.example.salesman.data.crossRef.ShopWithProducts
//
//@Dao
//interface ProductCrossDao {
//
//    @Transaction
//    @Query("SELECT * from Products")
//    fun getProductWithOrders(): List<ProductWithOrders>
//
//    @Transaction
//    @Query("SELECT * from Orders")
//    fun getOrderWithProducts(): List<OrderWithProducts>
//
//
//}