package com.example.salesman.ui.orders

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.salesman.R
import com.example.salesman.adapters.OrderAdapter
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.databinding.ActivityOrdersBinding

class Orders : AppCompatActivity() {
    private lateinit var binding :ActivityOrdersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title=getString(R.string.orders)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ordersAddFab.setOnClickListener {
            startActivity(Intent(this,AddOrder::class.java))

        }
        binding.imageView2.setOnClickListener {
            finish()
        }



    }
    override fun onStart() {
        super.onStart()

        lifecycleScope.launchWhenCreated {
            val listOfOrder =
                MyRoomDatabase.getDatabase(this@Orders).orderDao().getAllOrders()
            val adapter = OrderAdapter(this@Orders,listOfOrder )
            binding.ordersRv.adapter = adapter
        }


    }

}