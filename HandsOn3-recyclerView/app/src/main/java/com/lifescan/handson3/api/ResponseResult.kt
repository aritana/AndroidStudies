package com.lifescan.handson3.api


data class ResponseResult(

   val id:Int,
   val bio:String,
   val login: String,
   val avatar_url: String,
   val followers : Int

)