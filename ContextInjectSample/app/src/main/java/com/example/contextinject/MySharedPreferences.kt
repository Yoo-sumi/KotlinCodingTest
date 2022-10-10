package com.example.contextinject

import android.app.Application
import android.content.Context

class MySharedPreferences private constructor(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    private val editor =
        sharedPreferences.edit()

    fun saveText(text: String) {
        editor.putString(PREFERENCE_TEXT_KEY, text)
        editor.commit()
    }

    fun loadText(): String? =
        sharedPreferences.getString(PREFERENCE_TEXT_KEY, null)

    companion object {
        private const val PREFERENCE_TEXT_KEY = "PREFERENCE_TEXT_KEY"

        private var instance: MySharedPreferences? = null

        fun getInstance(
            application: Application,
        ): MySharedPreferences {
            val cached = instance
            return cached ?: MySharedPreferences(application.applicationContext)
                .also { created -> instance = created }
        }
    }
}