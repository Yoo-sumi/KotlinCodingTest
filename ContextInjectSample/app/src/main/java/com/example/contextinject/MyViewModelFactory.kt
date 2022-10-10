package com.example.contextinject

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MyViewModelFactory private constructor(
    private val application: Application,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(MainViewModel::class.java) -> createMainViewModel() as T
        else -> throw IllegalArgumentException("UnknownViewModelclass:${modelClass.name}")
    }

    private fun createMainViewModel(): MainViewModel {
        val sharedPreferences = MySharedPreferences.getInstance(application)
        val assetLoader = MyAssetLoader.getInstance(application)
        return MainViewModel(sharedPreferences, assetLoader)
    }

    companion object {
        private var instance: MyViewModelFactory? = null

        fun getInstance(
            application: Application,
        ): MyViewModelFactory {
            val cached = instance
            return cached ?: MyViewModelFactory(application).also { created -> instance = created }
        }
    }
}