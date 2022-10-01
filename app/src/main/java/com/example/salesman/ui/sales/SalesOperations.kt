package com.example.salesman.ui.sales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.salesman.R
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.databinding.ActivitySalesOperationsBinding

class SalesOperations : AppCompatActivity() {
    private lateinit var binding : ActivitySalesOperationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title=getString(R.string.sales_operation)
        binding = ActivitySalesOperationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.salesOperationsAddFab.setOnClickListener {
            startActivity(Intent(this,AddSalesOperations::class.java))

        }
        binding.imageView2.setOnClickListener {
            finish()
        }


    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launchWhenCreated {
            val listOfSales = MyRoomDatabase.getDatabase(this@SalesOperations).salesOperationDao().getSales("sales")
            val adapter = SalesAdapter(this@SalesOperations,listOfSales )
            binding.salesOperationsRv.adapter = adapter
        }


    }
}