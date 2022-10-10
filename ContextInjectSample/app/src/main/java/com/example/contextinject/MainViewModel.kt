package com.example.contextinject

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val mySharedPreferences: MySharedPreferences,
    private val myRepository: MyAssetLoader,
) : ViewModel()