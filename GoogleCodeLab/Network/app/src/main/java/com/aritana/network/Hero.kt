package com.aritana.network

import com.google.gson.annotations.SerializedName

data class Hero(

    val id: Int,
    val name: String,
    val slug: String,
    val images: Images,
    val appearance :Appearance

)





