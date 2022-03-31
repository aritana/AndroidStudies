package com.bootcamp.handson.di

import com.bootcamp.handson.ui.loadImage.LoadImageViewModel
import com.bootcamp.handson.ui.loadJson.LoadJsonViewModel
import com.bootcamp.handson.ui.loadList.LoadListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoadImageViewModel(get())
    }

    viewModel {
        LoadJsonViewModel(get())
    }

    viewModel {
        LoadListViewModel(get())
    }
}