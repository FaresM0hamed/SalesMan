package com.example.salesman.ui.shops

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.salesman.data.utils.MyRoomDatabase
import com.example.salesman.databinding.ActivityAddStoreBinding
import com.example.salesman.model.Shops
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.EasyPermissions
import java.text.SimpleDateFormat
import java.util.*

class AddStore : AppCompatActivity() {
    private lateinit var binding: ActivityAddStoreBinding
    private var isGetLocation = false
    private var isPermissionGranted = false
    private var storeLocation = ""
    private var lat = ""
    private var lng = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestLocation()

        val db = MyRoomDatabase.getDatabase(this)

        binding.addShopBtn.setOnClickListener {
            if (binding.addShopName.text.toString()
                    .isNotEmpty() && binding.addShopPhone.text.toString()
                    .isNotEmpty() && isGetLocation
            ) {

                lifecycleScope.launchWhenCreated {
                    try {
                        db.shopDao().addShop(
                            Shops(
                                0,
                                binding.addShopName.text.toString(),
                                binding.addShopLocation.text.toString(),
                                binding.addShopPhone.text.toString(),
                                "",
                                lat,
                                lng
                            )
                        )
                        runOnUiThread {
                            Toast.makeText(this@AddStore, "Successful", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@AddStore, "Failed to Add Store", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Please Complete fields", Toast.LENGTH_SHORT).show()
            }

        }
        binding.addShopLocation.isEnabled = false
        binding.imageView2.setOnClickListener {
            finish()
        }
    }

    private fun requestLocation() {
        if (EasyPermissions.hasPermissions(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            Log.e("---------------", "granted")
            isPermissionGranted = true
            getStoreLocation()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You have to allow location permission to set store location",
                10,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            isPermissionGranted = false
        }
    }

    @SuppressLint("MissingPermission")
    private fun getStoreLocation(): String {
        val mLocationRequest: LocationRequest = LocationRequest.create()
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val mLocationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    if (location != null && !isGetLocation) {
                        lat = location.latitude.toString()
                        lng = location.longitude.toString()

                        storeLocation = "${location.latitude},${location.longitude}"
                        Log.e("Location is:", storeLocation)
                        binding.addShopLocation.setText(storeLocation)
                        isGetLocation = true
                    }
                }
            }
        }

        LocationServices.getFusedLocationProviderClient(this)
            .requestLocationUpdates(
                mLocationRequest,
                mLocationCallback,
                mainLooper
            )

        LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnSuccessListener { location -> }

        return storeLocation
    }


}