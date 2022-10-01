package com.example.salesman.adapters

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.salesman.R
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.model.Shops
import com.example.salesman.ui.shops.AddStore
import com.example.salesman.ui.shops.Stores
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ShopAdapter(val activity: Activity, val shop: List<Shops>) :
    RecyclerView.Adapter<ShopAdapter.ShopVH>() {
    class ShopVH(v: View) : RecyclerView.ViewHolder(v) {
        val stores_tv_storeName: TextView = v.findViewById(R.id.stores_tv_storeName)
//        val stores_tv_storeLocation: TextView = v.findViewById(R.id.stores_tv_storeLocation)
        val stores_tv_storePhone: TextView =v.findViewById(R.id.stores_tv_storePhone)
        val stores_tv_date: TextView =v.findViewById(R.id.stores_tv_date)
        val stores_img_reset: ImageView =v.findViewById(R.id.stores_img_reset)
        val stores_img_location: ImageView =v.findViewById(R.id.location_button)
        val stores_img_call: ImageView =v.findViewById(R.id.stores_img_call)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopVH {
        val h = activity.layoutInflater.inflate(R.layout.stores_item, parent, false)
        return ShopVH(h)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ShopVH, position: Int) {
        holder.stores_tv_storeName.text = shop[position].name
        holder.stores_tv_date.text = shop[position].date
        holder.stores_tv_storePhone.text=shop[position].phone
        holder.stores_img_call.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:${shop[position].phone}")

            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                activity.startActivity(callIntent)
            } else {
                activity.requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 1)
            }
        }

        holder.stores_img_location.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?q=loc:${shop[position].lat},${shop[position].lng}(${shop[position].name})")
            )
            activity.startActivity(intent)
        }

        holder.stores_img_reset.setOnClickListener {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            GlobalScope.launch {
                try {
                    val storeDao = MyRoomDatabase.getDatabase(activity).shopDao()
                    storeDao.updateDate(date, shop[position].shopId)

                    val intent = Intent(activity, Stores::class.java)

                    activity.startActivity(intent)
                    activity.finish()

                } catch (e: Exception) {
//                    Toast.makeText(activity, "Failed to change last visit date", Toast.LENGTH_SHORT)
//                        .show()
                }
            }

        }

    }

    override fun getItemCount() = shop.size
}