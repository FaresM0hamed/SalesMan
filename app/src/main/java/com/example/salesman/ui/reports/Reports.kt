package com.example.salesman.ui.reports

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.salesman.R
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.databinding.ActivityReportsBinding
import java.text.SimpleDateFormat
import java.util.*

class Reports : AppCompatActivity() {
    private var soldPieces = ""
    private var salesprocess = ""
    private var damageReport = ""

    private lateinit var binding: ActivityReportsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.reports)

        binding.imageView2.setOnClickListener {
            finish()
        }


        val db = MyRoomDatabase.getDatabase(this)
        //get products
        lifecycleScope.launchWhenCreated {
            try {
                val sales = db.salesOperationDao().getSales("sales")
                for (sale in sales) {
                    val listProducts = sale.products.split(",")
                    var allProducts = ""
                    for (product in listProducts) {
                        if (allProducts == "") {
                            allProducts += product
                        } else {
                            allProducts += "\n"
                            allProducts += product
                        }
                    }
                    if (soldPieces == "") {
                        soldPieces += "Shop Name:${sale.shop_name} \n Products Sold:\n$allProducts"
                    } else {
                        soldPieces += "\n"
                        soldPieces += "\n"
                        soldPieces += "Shop Name:${sale.shop_name} \n Products Sold:\n$allProducts"
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@Reports, "Please Add sale First", Toast.LENGTH_SHORT
                ).show()
            }
        }

        //sales products
        lifecycleScope.launchWhenCreated {
            try {
                val sales = db.salesOperationDao().getSales("sales")
                for (sale in sales) {
                    if (salesprocess == "") {
                        salesprocess += "Date:${sale.date} \n Shop Name:${sale.shop_name} \n Products:${sale.products} \n Total Paid:${sale.totalPaid}"
                    } else {
                        salesprocess += "\n"
                        salesprocess += "\n"
                        salesprocess += "Date:${sale.date} \n Shop Name:${sale.shop_name} \n Products:${sale.products} \n Total Paid:${sale.totalPaid}"
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@Reports, "Please Add sale First", Toast.LENGTH_SHORT
                ).show()
            }
        }

        //damaged products
        lifecycleScope.launchWhenCreated {
            try {
                val requests = db.maintenanceDao().getAllMaintenance()!!
                for (request in requests) {
                    if (damageReport == "") {
                        damageReport += "Product:${request.productName} \n Damage Type:${request.maintenanceType} \n Damage Description:${request.maintenanceDes}"
                    } else {
                        damageReport += "\n"
                        damageReport += "\n"
                        damageReport += "Product:${request.productName} \n Damage Type:${request.maintenanceType} \n Damage Description:${request.maintenanceDes}"
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@Reports, "Please Add maintenance requests First", Toast.LENGTH_SHORT
                ).show()
            }
        }

        //products
        binding.shareSoldPieces.setOnClickListener {
            if (soldPieces.isNotEmpty()) {
                sendEmail("Products Sold", soldPieces)
            } else {
                Toast.makeText(
                    this@Reports, "Failed to send", Toast.LENGTH_SHORT
                ).show()
            }
        }
        //sales
        binding.shareSalesProcess.setOnClickListener {
            if (salesprocess.isNotEmpty()) {
                sendEmail("Sales", salesprocess)
            } else {
                Toast.makeText(
                    this@Reports, "Failed to send", Toast.LENGTH_SHORT
                ).show()
            }
        }
        //damages
        binding.shareDamagePieces.setOnClickListener {
            if (damageReport.isNotEmpty()) {
                sendEmail("Damages", damageReport)
            } else {
                Toast.makeText(
                    this@Reports, "Failed to send", Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    fun sendEmail(title: String, message: String) {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_SUBJECT, "$title in $date")
        i.putExtra(Intent.EXTRA_TEXT, message)
        try {
            startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "There are no email clients installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

 }






