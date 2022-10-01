package com.example.salesman.ui.orders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.lifecycle.lifecycleScope
import com.example.salesman.R
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.data.utils.SpinnerHandler
import com.example.salesman.databinding.ActivityAddOrderBinding
import com.example.salesman.model.Orders
import com.example.salesman.model.Products
import com.example.salesman.model.Shops
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class AddOrder : AppCompatActivity() {
    private lateinit var binding: ActivityAddOrderBinding
    private var name = ""
    private var shopName = ""
    private var productName = ""
    private var isSpinnerClickable = false
    private lateinit var products: List<Products>
    private lateinit var stores: List<Shops>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnClickListener {
            finish()
        }

        val db = MyRoomDatabase.getDatabase(this)

        //get shop
        lifecycleScope.launchWhenCreated {
            try {
                val stores = MyRoomDatabase.getDatabase(this@AddOrder).shopDao().getAllShops()
                val storesList = ArrayList<String>()
                //hint
                storesList.add("choose store")
//فلتره للعناصر عشان عايز منها الاسم
                for (store in stores) {
                    storesList.add(store.name)
                }


                SpinnerHandler(this@AddOrder, binding.addShopName, storesList) { value, index ->
                    if (index != 0) {
                        shopName = value
                    }
                }


                isSpinnerClickable = true

            } catch (e: Exception) {
                Log.e("/*/*/*", e.message.toString())
                Toast.makeText(this@AddOrder, "Failed to load stores", Toast.LENGTH_SHORT).show()
            }
        }

        SpinnerHandler(
            this, binding.chooseProductName, resources.getStringArray(R.array.products).asList()) { value, index ->
            if (index!=0){
            name = value
            }
        }

        binding.addOrderBtn.setOnClickListener {
            val quantity= binding.addQuantity.text.toString()
            Log.e("/**//*/",quantity)

            if (name.isNotEmpty() && shopName.isNotEmpty() && quantity.isNotEmpty()
            ) {

                lifecycleScope.launchWhenCreated {

                        db.orderDao().addOrder(
                            Orders(0, quantity.toInt(), shopName, name)
                        )
                        finish()
                        Toast.makeText(this@AddOrder, "Successful", Toast.LENGTH_SHORT).show()

                }

            } else
                Toast.makeText(this, "Please Complete Fields", Toast.LENGTH_SHORT).show()


        }


    }
}


//        //get products
//        lifecycleScope.launchWhenCreated {
//            try {
//                val productDao =
//                    MyRoomDatabase.getDatabase(this@AddOrder).productDao()
//                val products = productDao.getAllProducts()
//
//                val productList = ArrayList<String>()
//                //hint
//                productList.add("choose product")
//
//                for (product in products) {
//                    productList.add(product.productName)
//                }
//
//                SpinnerHandler(
//                    this@AddOrder, binding.addProductName, products) { value, index ->
//                    if (index != 0) {
//                        productName = value
//                    }
//                }
//
//            } catch (e: Exception) {
//                Toast.makeText(this@AddOrder, "Failed to load products", Toast.LENGTH_SHORT).show()
//            }
//        }