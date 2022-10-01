package com.example.salesman.data.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.salesman.data.daos.*
import com.example.salesman.model.*
//import com.example.salesman.data.crossRef.ProductsCrossRef
//import com.example.salesman.data.crossRef.SalesOperationCrossRef

//import com.example.salesman.data.utils.Converters

@Database(entities = [Maintenance::class,Orders::class,Products::class,Shops::class,Sale::class],
    version = 1, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class MyRoomDatabase:RoomDatabase() {

    abstract fun maintenanceDao(): MaintenanceDao
    abstract fun orderDao(): OrderDao
    abstract fun productDao(): ProductDao
//    abstract fun productCrossDao(): ProductCrossDao
    abstract fun salesOperationDao(): SalesOperationDao
    abstract fun shopDao(): ShopDao

    companion object {
        @Volatile
        private var INSTANCE: MyRoomDatabase? = null
        fun getDatabase(context: Context): MyRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyRoomDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}