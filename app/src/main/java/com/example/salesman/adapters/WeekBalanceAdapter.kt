package com.example.salesman.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesman.R
import com.example.salesman.model.Products

class WeekBalanceAdapter(val activity: Activity, val product: List<Products>) :RecyclerView.Adapter<WeekBalanceAdapter.WeekBalanceVH>() {
    class WeekBalanceVH(v: View) : RecyclerView.ViewHolder(v) {
        val weekBalanceTvProductName:TextView=v.findViewById(R.id.weekBalance_tv_productName)
        val weekBalanceTvNumOfProduct:TextView=v.findViewById(R.id.weekBalance_tv_numOfProduct)
        val weekBalanceTvPriceOfProduct:TextView=v.findViewById(R.id.weekBalance_tv_priceOfProduct)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekBalanceVH {
        val h = activity.layoutInflater.inflate(R.layout.weekbalnace_item, parent, false)
        return WeekBalanceVH(h)
    }

    override fun onBindViewHolder(holder: WeekBalanceVH, position: Int) {
    holder.weekBalanceTvProductName.text=product[position].productName
    holder.weekBalanceTvNumOfProduct.text= product[position].balance.toString()
    holder.weekBalanceTvPriceOfProduct.text= product[position].price.toString()
    }

    override fun getItemCount() = product.size


}