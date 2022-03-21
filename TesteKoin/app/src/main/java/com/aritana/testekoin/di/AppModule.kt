package com.aritana.testekoin.di

import com.aritana.testekoin.presenter.Car
import com.aritana.testekoin.presenter.MySimplePresenter
import com.aritana.testekoin.repository.Engine
import com.aritana.testekoin.repository.EngineImpl
import com.aritana.testekoin.repository.HelloRepository
import com.aritana.testekoin.repository.HelloRepositoryImpl
import org.koin.dsl.module


var appModule = module{

    single<HelloRepository>{HelloRepositoryImpl()}
    factory { MySimplePresenter(get()) }

    single<Engine>{EngineImpl()}
    factory<Car> { Car(get()) }
}
