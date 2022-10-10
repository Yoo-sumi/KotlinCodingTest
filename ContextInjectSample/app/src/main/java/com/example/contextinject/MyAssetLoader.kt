package com.example.contextinject

import android.app.Application
import android.content.res.Resources

class MyAssetLoader private constructor(private val resources: Resources) {

    fun loadAsset(filePath: String): String {
        val inputStream = resources.assets.open(filePath)
        val size = inputStream.available()
        val bytes = ByteArray(size)
        inputStream.read(bytes)
        return String(bytes)
    }

    companion object {
        private var instance: MyAssetLoader? = null

        fun getInstance(
            application: Application,
        ): MyAssetLoader {
            val cached = instance
            return cached ?: MyAssetLoader(application.resources)
                .also { created -> instance = created }
        }
    }
}