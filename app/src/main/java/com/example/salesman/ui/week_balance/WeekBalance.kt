package com.example.salesman.ui.week_balance

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.salesman.R
import com.example.salesman.adapters.WeekBalanceAdapter
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.databinding.ActivityWeekBalanceBinding


class WeekBalance : AppCompatActivity() {
    private lateinit var binding :ActivityWeekBalanceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeekBalanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.show()
        title = getString(R.string.week_balance)
        val db = MyRoomDatabase.getDatabase(this)
       //go to addWeekBalance Activity
        binding.weekBalanceAddFab.setOnClickListener {
            startActivity(Intent(this, WeekBalanceAdd::class.java))
        }
        //Delete allProducts
        binding.weekBalanceResetFab.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                db.productDao().deleteAllProducts()
            }
            finish()
        }
        binding.imageView2.setOnClickListener {
            finish()
        }
    }



    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenCreated {
            val listOfProduct = MyRoomDatabase.getDatabase(this@WeekBalance).productDao().getAllProducts()
            val adapter = WeekBalanceAdapter(this@WeekBalance, listOfProduct)
            binding.weekBalanceRv.adapter = adapter
        }


    }
}