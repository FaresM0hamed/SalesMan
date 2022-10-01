package com.example.salesman.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesman.R
import com.example.salesman.model.Orders

class OrderAdapter(val activity: Activity, val order: List<Orders>) :
    RecyclerView.Adapter<OrderAdapter.OrderVH>() {
    class OrderVH(v: View) : RecyclerView.ViewHolder(v) {
        val orders_tv_productName: TextView =v.findViewById(R.id.orders_tv_productName)
        val orders_tv_shopeName: TextView =v.findViewById(R.id.orders_tv_shopeName)
        val orders_tv_quantity: TextView =v.findViewById(R.id.orders_tv_quantity)
//        val orders_tv_date: TextView =v.findViewById(R.id.orders_tv_date)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderVH {
        val h = activity.layoutInflater.inflate(R.layout.orders_item, parent, false)
        return OrderVH(h)
    }

    override fun onBindViewHolder(holder: OrderVH, position: Int) {
        holder.orders_tv_productName.text=order[position].product_name
        holder.orders_tv_shopeName.text= order[position].shop_name
        holder.orders_tv_quantity.text= order[position].quantity.toString()
//        holder.orders_tv_date.text= order[position].date
    }

    override fun getItemCount() = order.size

}