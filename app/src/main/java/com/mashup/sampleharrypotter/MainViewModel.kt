package com.mashup.sampleharrypotter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.sampleharrypotter.data.UnsplashImage
import com.mashup.sampleharrypotter.data.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) : ViewModel() {

    private val atomicInteger: AtomicInteger = AtomicInteger()

    private val _photos = MutableStateFlow<List<UnsplashImage>>(emptyList())
    val photos: StateFlow<List<UnsplashImage>> = _photos

    init {
        fetchImages()
    }

    fun fetchImages() {
        _photos.value = emptyList()
        viewModelScope.launch(Dispatchers.IO) {
            repeat(3) {
                _photos.value += unsplashRepository.getPhotos(atomicInteger.getAndIncrement())
                    ?: emptyList()
            }
        }
    }
}