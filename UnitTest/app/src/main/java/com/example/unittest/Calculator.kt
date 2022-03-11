package com.example.unittest

import java.lang.Math.abs

class Calculator(private val secreteConversion: SecreteConversion) {

    fun add(a:Int,b:Int)=a+b
    fun sub(a:Int,b:Int)=a-b
    fun mul(a:Int,b:Int)=a*b
    fun absolute(a:Int)=abs(a)
    fun pow(a:Double,b:Double):Double= Math.pow(a,b)


    fun myCrazyCalculation(a:Int, b:Int) = when{
        isMyFavoriteNumber(a)->{
            mul(a,b)
        }
        else->CRAZY_NUMBER
    }

    private fun isMyFavoriteNumber(a:Int)= a == MY_FAVORITE_NUMBER

    companion object{
        const val  MY_FAVORITE_NUMBER =7
        const val  CRAZY_NUMBER =19
    }
}