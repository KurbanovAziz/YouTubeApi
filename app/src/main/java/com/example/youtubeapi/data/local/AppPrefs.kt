package com.example.youtubeapi.data.local

import android.content.Context

class AppPrefs(context: Context) {
    private val prefs = context.getSharedPreferences("ololo", Context.MODE_PRIVATE)

    var onBoard:Boolean
        get() = prefs.getBoolean("kayrat",false)
        set(value) = prefs.edit().putBoolean("kayrat",value).apply()
}