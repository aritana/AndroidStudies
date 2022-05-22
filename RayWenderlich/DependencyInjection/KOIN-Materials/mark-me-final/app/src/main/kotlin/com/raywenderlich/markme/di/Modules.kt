/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.markme.di

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.raywenderlich.markme.feature.FeatureContract
import com.raywenderlich.markme.feature.presenter.FeaturePresenter
import com.raywenderlich.markme.main.MainContract
import com.raywenderlich.markme.main.presenter.MainPresenter
import com.raywenderlich.markme.model.Student
import com.raywenderlich.markme.model.database.AppDatabase
import com.raywenderlich.markme.repository.AppRepository
import com.raywenderlich.markme.splash.SplashContract
import com.raywenderlich.markme.splash.presenter.SplashPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val applicationModule = module(override = true) {
    factory<SplashContract.Presenter> { (view: SplashContract.View) -> SplashPresenter(view) }
    factory<MainContract.Presenter> { (view: MainContract.View) -> MainPresenter(view) }
    factory<FeatureContract.Presenter<Student>> { (view: FeatureContract.View<Student>) -> FeaturePresenter(view) }
    single<FeatureContract.Model<Student>> { AppRepository }
    single<SharedPreferences> { androidContext().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE) }
    single {
        Room.databaseBuilder(androidContext(),
                AppDatabase::class.java, "app-database").build()
    }
}
