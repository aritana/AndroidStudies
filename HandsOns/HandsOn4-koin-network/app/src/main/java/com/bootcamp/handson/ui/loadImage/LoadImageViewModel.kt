package com.bootcamp.handson.ui.loadImage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.handson.repository.LoadImageRepository

class LoadImageViewModel(
    // Inject
    private val loadImageRepository: LoadImageRepository
) : ViewModel() {

    private val _image = MutableLiveData<String>()
    val image: LiveData<String>
        get() = _image

    private val _isImageFailed = MutableLiveData<Boolean>()
    val isImageFailed: LiveData<Boolean>
        get() = _isImageFailed

    fun getHeroImage(heroId: Int) {
        loadImageRepository.getHeroImage(heroId) {
            /*
            Precisamos usar o PostValue para entregar o valor ao LiveData
            depois da request na API finalizar.
             */
            if (it == "Failed to load image!") {
                _isImageFailed.value = true
                _image.postValue("")
            } else {
                _isImageFailed.value = false
                _image.postValue(it)
            }
        }
    }
}