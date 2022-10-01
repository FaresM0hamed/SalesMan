package com.example.salesman.data.daos

import androidx.room.*

import com.example.salesman.model.Sale
import com.example.salesman.ui.sales.SalesOperations

@Dao
//@TypeConverters(Converters::class)
interface SalesOperationDao {
    @Insert
    suspend fun insertSale(sale: Sale)

    @Query("select * from sales_table WHERE type=:type")
    suspend fun getSales(type:String) : List<Sale>

}