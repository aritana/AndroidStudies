package com.lifescan.network.loadList

import com.google.gson.annotations.SerializedName

class LoadListResult(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String
)