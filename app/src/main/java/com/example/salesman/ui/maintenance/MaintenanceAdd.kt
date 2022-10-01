package com.example.salesman.ui.maintenance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.salesman.R
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.data.utils.SpinnerHandler
import com.example.salesman.databinding.ActivityMaintenanceAddBinding
import com.example.salesman.model.Maintenance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class MaintenanceAdd : AppCompatActivity() {
   private var name = ""
    private var damage = ""
    private var shopName=""
    private lateinit var binding: ActivityMaintenanceAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityMaintenanceAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MyRoomDatabase.getDatabase(this)
        binding.imageView2.setOnClickListener {
            finish()
        }

        //get shop
        lifecycleScope.launchWhenCreated {
            try {
                val stores = MyRoomDatabase.getDatabase(this@MaintenanceAdd).shopDao().getAllShops()
                val storesList = ArrayList<String>()
                //hint
                storesList.add("choose store")
//فلتره للعناصر عشان عايز منها الاسم
                for (store in stores) {
                    storesList.add(store.name)
                }


                SpinnerHandler(this@MaintenanceAdd, binding.shopName, storesList) { value, index ->
                    if (index != 0) {
                         shopName = value
                    }
                }


                var isSpinnerClickable = true

            } catch (e: Exception) {
                Log.e("/*/*/*",e.message.toString())
                Toast.makeText(this@MaintenanceAdd, "Failed to load stores", Toast.LENGTH_SHORT).show()
            }
        }

//get productList
        SpinnerHandler(
            this, binding.productName, resources.getStringArray(R.array.products).asList()) { value, index ->
           if (index!=0){
            name = value
           }
        }

        //get damageList
        SpinnerHandler(
            this, binding.damageType, resources.getStringArray(R.array.damages).asList()) { value, index ->
            if (index!=0){
             damage = value
            }
        }






        binding.save.setOnClickListener {
            val desc=binding.damageDec.text.toString()
            if (name.isNotEmpty() && shopName.isNotEmpty() && damage.isNotEmpty()&&desc.isNotEmpty()) {
                lifecycleScope.launchWhenCreated {
                    db.maintenanceDao().addMaintenance(
                        Maintenance(
                            0,
                            name,
                            shopName,
                            damage,
                            desc,
                            false
                        )
                    )
                }
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()


            }else
                Toast.makeText(this, "Please Complete Fields", Toast.LENGTH_SHORT).show()
        }
    }
}