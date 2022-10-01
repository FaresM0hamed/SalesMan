package com.example.salesman.data.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.salesman.R

class SpinnerHandler(

    private var context: Context,
    private var spinner: Spinner,
    private var items: List<String>,
    private var callback : ((value:String , index:Int)->Unit)


) {

    init {
        //adapter
        val spinnerAdapter = object : ArrayAdapter<String>(context, androidx.transition.R.layout.support_simple_spinner_dropdown_item, items) {

            override fun isEnabled(position: Int): Boolean {
                // Disable the first item from Spinner
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup?
            ): View {
                val view = super.getDropDownView(position, convertView, parent!!)
                val tv = view as TextView
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                return view
            }
        }

        //how select item will looks like
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item)

        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                if (position != 0) {
                    spinner.solidColor

                    (adapterView.getChildAt(0) as TextView)
                }
                callback.invoke(items[position],position)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {

            }
        }

    }






}