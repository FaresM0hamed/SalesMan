package com.example.salesman.data.daos

import androidx.room.*
import com.example.salesman.model.Maintenance

@Dao
interface MaintenanceDao {

    @Insert
    suspend fun addMaintenance(maintenance: Maintenance)

    @Query("UPDATE maintenance_table SET isFixed=:is_fixed WHERE maintenance_id = :id")
    suspend fun updateFixStatus(is_fixed:Boolean,id:Int)

    @Query("SELECT * from maintenance_table")
   suspend fun getAllMaintenance():List<Maintenance>
}