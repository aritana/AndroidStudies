package com.bootcamp.handson.di

import com.bootcamp.handson.repository.LoadImageRepository
import com.bootcamp.handson.repository.LoadJsonRepository
import com.bootcamp.handson.repository.LoadListRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { LoadImageRepository(get()) }
    single { LoadJsonRepository(get()) }
    single { LoadListRepository(get()) }
}