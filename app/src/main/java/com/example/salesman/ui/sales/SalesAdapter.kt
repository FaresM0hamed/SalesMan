package com.example.salesman.ui.sales

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesman.R
import com.example.salesman.model.Sale

class SalesAdapter(val activity: Activity, val sale: List<Sale>) :
    RecyclerView.Adapter<SalesAdapter.SalesVH>() {
    class SalesVH(v: View) : RecyclerView.ViewHolder(v) {
        val salesOperations_tv_productName: TextView =v.findViewById(R.id.salesOperations_tv_productName)
        val salesOperations_tv_shopeName: TextView =v.findViewById(R.id.salesOperations_tv_shopeName)
        val salesOperations_tv_money: TextView =v.findViewById(R.id.salesOperations_tv_money)
        val salesOperations_tv_date: TextView =v.findViewById(R.id.salesOperations_tv_date)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesVH {
        val h = activity.layoutInflater.inflate(R.layout.sales_operations_item, parent, false)
        return SalesVH(h)
    }

    override fun onBindViewHolder(holder: SalesVH, position: Int) {
        holder.salesOperations_tv_productName.text=sale[position].products
        holder.salesOperations_tv_shopeName.text= sale[position].shop_name
        holder.salesOperations_tv_money.text= sale[position].totalPaid.toString()
        holder.salesOperations_tv_date.text= sale[position].date
    }

    override fun getItemCount() = sale.size
}