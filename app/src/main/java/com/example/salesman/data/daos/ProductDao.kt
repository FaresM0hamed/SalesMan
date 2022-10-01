package com.example.salesman.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.salesman.model.Products

@Dao
interface ProductDao {

    @Insert
    suspend fun addProduct(product: Products)

    @Query("UPDATE products_table SET balance=:newBalance WHERE productId = :productId")
    suspend fun updateBalance(newBalance: Int, productId: Int)
    @Query("SELECT * from products_table")
    suspend fun getAllProducts(): List<Products>
    @Query("DELETE  FROM products_table ")
    suspend fun deleteAllProducts()


}