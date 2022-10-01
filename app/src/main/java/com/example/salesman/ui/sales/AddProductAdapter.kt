package com.example.salesman.ui.sales

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesman.R
import com.example.salesman.model.SaleProduct

class AddProductAdapter(
    private val context: Context,
    private val dataSet: List<SaleProduct>,
) : RecyclerView.Adapter<AddProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val productName : TextView
        val amount : TextView
        val totalPrice : TextView
        init {
            productName = view.findViewById(R.id.product_name)
            amount = view.findViewById(R.id.amount)
            totalPrice = view.findViewById(R.id.total_price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_add_product, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.productName.text = dataSet[position].name
        holder.amount.text = dataSet[position].amount.toString()
        holder.totalPrice.text = dataSet[position].total.toString()

    }

    override fun getItemCount() = dataSet.size
}
