package com.bootcamp.handson.ui.loadJson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.handson.repository.LoadJsonRepository

class LoadJsonViewModel(
    private val loadJsonRepository: LoadJsonRepository
) : ViewModel() {

    private val _jsonText = MutableLiveData<String>()
    val jsonText: LiveData<String>
        get() = _jsonText

    fun getJson() {
        loadJsonRepository.getJson {
            _jsonText.postValue(it)
        }
    }
}