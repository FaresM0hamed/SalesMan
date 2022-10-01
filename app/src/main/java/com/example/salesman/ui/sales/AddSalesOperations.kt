package com.example.salesman.ui.sales

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.data.utils.SpinnerHandler
import com.example.salesman.databinding.ActivityAddSalesOperationsBinding
import com.example.salesman.model.Products
import com.example.salesman.model.Sale
import com.example.salesman.model.SaleProduct
import com.example.salesman.model.Shops
import java.text.SimpleDateFormat
import java.util.*

class AddSalesOperations : AppCompatActivity() {
    private lateinit var binding: ActivityAddSalesOperationsBinding
    private var isSpinnerClickable = false

    private var shopName = ""
    private var saleProducts = ""
    private var totalPrice: Double = 0.00

    private var saleProductList = mutableListOf<SaleProduct>()

    private lateinit var products: List<Products>
    private lateinit var shops: List<Shops>

    private  var chosenProducts= mutableListOf<Products>()
    private  var chosenAmounts= mutableListOf<Int>()
    private var indexOfProduct = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddSalesOperationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnClickListener {
            finish()
        }

        //get products
        lifecycleScope.launchWhenCreated {
            try {
                val productDao = MyRoomDatabase.getDatabase(this@AddSalesOperations).productDao()
                products = productDao.getAllProducts()!!

                val productList = ArrayList<String>()
                //hint
                productList.add("choose product")

                for (product in products) {
                    productList.add(product.productName)
                }

                SpinnerHandler(
                    this@AddSalesOperations,
                    binding.productSpinner,
                    productList
                ) { value, index ->
                    if (index != 0) {
                        if (saleProducts == "") {
                            saleProducts += value
                        } else {
                            saleProducts += ",$value"
                        }
                        indexOfProduct = index
                    }
                }
                isSpinnerClickable = true

            } catch (e: Exception) {
                Toast.makeText(
                    this@AddSalesOperations,
                    "Failed to load products", Toast.LENGTH_SHORT
                ).show()
            }
        }


        //get stores
        lifecycleScope.launchWhenCreated {
            try {
                val storeDao = MyRoomDatabase.getDatabase(this@AddSalesOperations).shopDao()
                shops = storeDao.getAllShops()!!

                val storesList = ArrayList<String>()
                //hint
                storesList.add("choose store")

                for (store in shops) {
                    storesList.add(store.name)
                }

                SpinnerHandler(
                    this@AddSalesOperations,
                    binding.storeSpinner,
                    storesList
                ) { value, index ->
                    if (index != 0) {
                        shopName = value
                    }
                }
                isSpinnerClickable = true

            } catch (e: Exception) {
                Toast.makeText(
                    this@AddSalesOperations,
                    "Failed to load stores", Toast.LENGTH_SHORT
                ).show()
            }
        }


        binding.addProduct.setOnClickListener {
            Log.e(saleProducts,saleProducts)
            if (binding.amountEt.text.isNotEmpty() && saleProducts.isNotEmpty()) {

                val selectProduct = products[indexOfProduct - 1]
                val amount = binding.amountEt.text.toString().toInt()

                if (selectProduct.balance >= amount) {

                    totalPrice += selectProduct.price * amount
                    binding.totalPrice.text = totalPrice.toString()


                    chosenProducts.add(selectProduct)
                    chosenAmounts.add(amount)

                    saleProductList.add(
                        SaleProduct(
                            indexOfProduct, selectProduct.productName, selectProduct.price, amount
                        )
                    )

                    val list: List<SaleProduct> = saleProductList
                    binding.productRecyclerView.layoutManager = LinearLayoutManager(this)
                    binding.productRecyclerView.adapter = AddProductAdapter(this, list)

                } else {
                    binding.amountEt.error = "No sufficient amount"
                }

            } else {
                Toast.makeText(this, "you have to select product and amount", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.addSaleButton.setOnClickListener {

            Log.e("shopName",shopName)
            Log.e("saleProducts",saleProducts)
            Log.e("totalPrice",totalPrice.toString())
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            if (shopName.isNotEmpty() && saleProducts.isNotEmpty()
                && totalPrice != 0.00
            ) {
                lifecycleScope.launchWhenCreated {
                    val db = MyRoomDatabase.getDatabase(this@AddSalesOperations)
                    val saleDao = db.salesOperationDao()
                    val productDao = db.productDao()

                    try {
                        saleDao.insertSale(
                            Sale(
                                0, date, shopName, saleProducts,"sales", totalPrice
                            )
                        )
                        for(i in 0 until chosenProducts.size){
                            val newBalance = chosenProducts[i].balance - chosenAmounts[i]
                            Log.e("newBalance",newBalance.toString())
                            productDao.updateBalance(
                                newBalance,
                                chosenProducts[i].productId
                            )
                        }

                        finish()
                    } catch (e: Exception) {
                        Log.e("e",e.message.toString())
                        Toast.makeText(
                            this@AddSalesOperations,
                            "Failed to add Product",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    this@AddSalesOperations,
                    "You have to complete fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }
}