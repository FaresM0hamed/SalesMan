package com.example.salesman.model

data class SaleProduct (

        val id: Int,
        val name: String,
        val price: Double,
        val amount: Int,
        val total: Double = price * amount


        )
