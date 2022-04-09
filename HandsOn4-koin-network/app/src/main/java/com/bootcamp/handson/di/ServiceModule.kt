package com.bootcamp.handson.di

import com.bootcamp.handson.network.LoadImageRetrofit
import com.bootcamp.handson.network.LoadJsonRetrofit
import com.bootcamp.handson.network.LoadListRetrofit
import org.koin.dsl.module

val serviceModule = module {
    single { LoadImageRetrofit().retrofitService }
    single { LoadJsonRetrofit().retrofitService }
    single { LoadListRetrofit().retrofitService }
}