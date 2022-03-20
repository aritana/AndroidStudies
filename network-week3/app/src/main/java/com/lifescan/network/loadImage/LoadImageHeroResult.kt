package com.lifescan.network.loadImage

import com.google.gson.annotations.SerializedName

class LoadImageHeroResult(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("slug")
    val slug: String,

    @field:SerializedName("images")
    val images: LoadImageImgResult
) {

    class LoadImageImgResult(
        @field:SerializedName("xs")
        val thumb: String,

        @field:SerializedName("sm")
        val small: String,

        @field:SerializedName("md")
        val medium: String,

        @field:SerializedName("lg")
        val large: String
    )
}