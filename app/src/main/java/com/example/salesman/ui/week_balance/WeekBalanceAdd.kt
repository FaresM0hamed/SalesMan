package com.example.salesman.ui.week_balance

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import androidx.lifecycle.lifecycleScope
import com.example.salesman.R
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.data.utils.SpinnerHandler
import com.example.salesman.databinding.ActivityWeekBalanceAddBinding
import com.example.salesman.model.Products

class WeekBalanceAdd : AppCompatActivity() {
    private lateinit var binding: ActivityWeekBalanceAddBinding
    private var name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWeekBalanceAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MyRoomDatabase.getDatabase(this)

        SpinnerHandler(
            this, binding.addProductNameSpinner, resources.getStringArray(R.array.products).asList()) { value,index ->
            if (index!=0){
            name = value
            }
        }

        binding.imageView2.setOnClickListener {
            finish()
        }
        binding.btnAdd.setOnClickListener {
            val price = binding.priceAdd.text.toString()
            val balance = binding.amountAdd.text.toString()

            if (name.isNotEmpty() && price.isNotEmpty() &&balance.isNotEmpty()) {

                lifecycleScope.launchWhenCreated {
                    try {
                        db.productDao().addProduct(
                            Products(0, name, price.toDouble(),balance.toInt()))
                        finish()
                        Toast.makeText(this@WeekBalanceAdd, "Successful", Toast.LENGTH_SHORT).show()
                    }catch (e:Exception){
                        Toast.makeText(this@WeekBalanceAdd, "Failed , try again", Toast.LENGTH_SHORT).show()
                    }

                }

            }else
                Toast.makeText(this, "Please Complete Fields", Toast.LENGTH_SHORT).show()
        }

    }
}