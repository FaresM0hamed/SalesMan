package com.example.salesman.data.utils

import android.content.Context
import com.example.salesman.R
import com.example.salesman.model.HomeElements
import com.example.salesman.model.Products

class MainDataSource {

    fun data (context:Context) : ArrayList<HomeElements>{
        val icons= arrayListOf<HomeElements>()
        icons.add(HomeElements(context.getString(R.string.week_balance),R.drawable.ic_week_balance_test))
        icons.add(HomeElements(context.getString(R.string.sales_operation), R.drawable.ic_salesssss_test))
        icons.add(HomeElements(context.getString(R.string.stores), R.drawable.ic_stores_test))
        icons.add(HomeElements(context.getString(R.string.maintenance), R.drawable.ic_maintenance_request_test))
        icons.add(HomeElements(context.getString(R.string.orders), R.drawable.ic_orders_test))
        icons.add(HomeElements(context.getString(R.string.reports), R.drawable.ic_reports_test))
        return icons



    }


}