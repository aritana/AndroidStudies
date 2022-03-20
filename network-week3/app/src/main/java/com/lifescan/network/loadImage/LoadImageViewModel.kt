package com.lifescan.network.loadImage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lifescan.network.repository.LoadImageRepository

class LoadImageViewModel(
    // Inject Repository
) : ViewModel() {

    private val loadImageRepository = LoadImageRepository()

    private val _image = MutableLiveData<String>()
    val image: LiveData<String>
        get() = _image

    fun getHeroImage(heroId: Int) {
        loadImageRepository.getHeroImage(heroId) {
            /*
            Precisamos usar o PostValue para entregar o valor ao LiveData
            depois da request na API finalizar.
             */
            _image.postValue(it)
        }
    }
}