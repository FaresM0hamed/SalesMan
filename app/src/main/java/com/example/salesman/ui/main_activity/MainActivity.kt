package com.example.salesman.ui.main_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.salesman.R
import com.example.salesman.adapters.IconsAdapter
import com.example.salesman.data.utils.MainDataSource
import com.example.salesman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.homepage)
        val list = MainDataSource().data(this)
        val adapter = IconsAdapter(this, list)
        binding.mainRv.adapter = adapter




    }
}


