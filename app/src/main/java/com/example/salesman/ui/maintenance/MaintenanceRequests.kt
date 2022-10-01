package com.example.salesman.ui.maintenance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.salesman.R
import com.example.salesman.adapters.MaintenanceRequestAdapter
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.databinding.ActivityMaintenanceRequestsBinding

class MaintenanceRequests : AppCompatActivity() {
    private lateinit var binding: ActivityMaintenanceRequestsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title=getString(R.string.maintenance)
        binding=ActivityMaintenanceRequestsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.maintenanceRequestsAddFab.setOnClickListener {
            startActivity(Intent(this,MaintenanceAdd::class.java))

        }
        binding.imageView2.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()

        val maintenanceDao = MyRoomDatabase.getDatabase(this).maintenanceDao()

        lifecycleScope.launchWhenCreated {
            try{
              val  listOfRequests =  maintenanceDao.getAllMaintenance()!!
               val adapter = MaintenanceRequestAdapter(this@MaintenanceRequests , listOfRequests)

                binding.maintenanceRequestsRv.adapter = adapter
            }catch (e:Exception){
                Toast.makeText(this@MaintenanceRequests,"Failed to load maintenance requests.",
                    Toast
                    .LENGTH_SHORT).show()
            }
        }
    }


}