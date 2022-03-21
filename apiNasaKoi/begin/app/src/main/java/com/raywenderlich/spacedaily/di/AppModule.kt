package com.raywenderlich.spacedaily.di

import com.raywenderlich.spacedaily.ActivityRetriever
import com.raywenderlich.spacedaily.DefaultCurrentActivityListener
import org.koin.dsl.module

var appModule = module{

    single { DefaultCurrentActivityListener() }
    single {ActivityRetriever(get())}

}