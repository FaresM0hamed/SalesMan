package com.example.salesman.ui.shops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.salesman.R
import com.example.salesman.adapters.ShopAdapter
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.databinding.ActivityStoresBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Stores : AppCompatActivity() {
    private lateinit var binding : ActivityStoresBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStoresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title=getString(R.string.stores)
        val storesAddFab:FloatingActionButton=findViewById(R.id.storesAddFab)
        storesAddFab.setOnClickListener {

            startActivity(Intent(this,AddStore::class.java))

        }
        binding.imageView2.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()


        lifecycleScope.launchWhenCreated {
            val storeDao = MyRoomDatabase.getDatabase(this@Stores).shopDao()
            val listOfShope =  storeDao.getAllShops()
           val adapter = ShopAdapter(this@Stores,listOfShope)

//            binding.storesRv.layoutManager = LinearLayoutManager(this@Stores)
            binding.storesRv.adapter = adapter
        }
    }

}