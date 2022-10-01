package com.example.salesman.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.salesman.R
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.model.Maintenance
import com.example.salesman.ui.maintenance.MaintenanceRequests
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MaintenanceRequestAdapter(val activity: Activity, val problem: List<Maintenance>) :
    RecyclerView.Adapter<MaintenanceRequestAdapter.MaintenanceVH>() {
    class MaintenanceVH(v: View) : RecyclerView.ViewHolder(v) {
        val maintenanceRequests_tv_productName: TextView =v.findViewById(R.id.maintenanceRequests_tv_productName)
        val maintenanceRequests_tv_shopeName: TextView =v.findViewById(R.id.maintenanceRequests_tv_shopeName)
        val maintenanceRequests_tv_descProblem: TextView =v.findViewById(R.id.maintenanceRequests_tv_descProblem)
        val maintenanceRequests_tv_type: TextView =v.findViewById(R.id.maintenanceRequests_tv_type)
       val maintenanceRequests_tv_isFIx: TextView =v.findViewById(R.id.maintenanceRequests_tv_isFIx)
       val fixbtn: Button =v.findViewById(R.id.fixBtn)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaintenanceVH {
        val h = activity.layoutInflater.inflate(R.layout.maintenance_requests_item, parent, false)
        return MaintenanceVH(h)
    }
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MaintenanceVH, position: Int) {
        holder.maintenanceRequests_tv_productName.text=problem[position].productName.toString()
        holder.maintenanceRequests_tv_shopeName.text= problem[position].shopName.toString()
        holder.maintenanceRequests_tv_descProblem.text= problem[position].maintenanceDes.toString()
        holder.maintenanceRequests_tv_type.text= problem[position].maintenanceType.toString()
        holder.maintenanceRequests_tv_isFIx.text= problem[position].isFixed.toString()

        if(problem[position].isFixed){
            holder.maintenanceRequests_tv_isFIx.text = "Fixed"
            holder.maintenanceRequests_tv_isFIx.setTextColor(Color.GREEN)
        }else{
            holder.maintenanceRequests_tv_isFIx.text = "Not Fixed Yet"
            holder.maintenanceRequests_tv_isFIx.setTextColor(Color.RED)
        }

        holder.fixbtn.setOnClickListener {
            if(!problem[position].isFixed){
                GlobalScope.launch {
                    try{
                        val dao = MyRoomDatabase.getDatabase(activity).maintenanceDao()
                        dao.updateFixStatus(true,problem[position]._id)
                        val intent = Intent(activity, MaintenanceRequests::class.java)
                        activity.finish()
                        activity.startActivity(intent)
                    }catch (e:Exception){
                        Toast.makeText(activity,"Failed to update fix status", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



    }

    override fun getItemCount() = problem.size
}