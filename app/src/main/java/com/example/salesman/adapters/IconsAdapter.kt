package com.example.salesman.adapters

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesman.R
import com.example.salesman.model.HomeElements
import com.example.salesman.ui.maintenance.MaintenanceRequests
import com.example.salesman.ui.orders.Orders
import com.example.salesman.ui.reports.Reports
import com.example.salesman.ui.sales.SalesOperations
import com.example.salesman.ui.shops.Stores
import com.example.salesman.ui.week_balance.WeekBalance


class IconsAdapter(val activity: Activity, val icon: ArrayList<HomeElements>) :
    RecyclerView.Adapter<IconsAdapter.IconsVH>() {
    class IconsVH(v: View) : RecyclerView.ViewHolder(v) {

        val parent: CardView = v.findViewById(R.id.main_parent)
        val img: ImageView = v.findViewById(R.id.main_img)
        val tv: TextView = v.findViewById(R.id.main_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconsVH {
        val h = activity.layoutInflater.inflate(R.layout.main_list_item, parent, false)
        return IconsVH(h)
    }

    override fun onBindViewHolder(holder: IconsVH, position: Int) {
        holder.parent.setOnClickListener {
            handleClick(position)
        }
        holder.tv.text = icon[position].name
        holder.img.setImageResource(icon[position].pic)
    }

    override fun getItemCount() = icon.size


    private fun handleClick(position: Int) {

        var intent = Intent()
        when (position) {
            0 -> intent = Intent(activity, WeekBalance::class.java)
            1 -> intent = Intent(activity, SalesOperations::class.java)
            2 -> intent = Intent(activity, Stores::class.java)
            3 -> intent = Intent(activity, MaintenanceRequests::class.java)
            4 -> intent = Intent(activity, Orders::class.java)
            5 -> intent = Intent(activity, Reports::class.java)
        }
        activity.startActivity(intent)
    }

}