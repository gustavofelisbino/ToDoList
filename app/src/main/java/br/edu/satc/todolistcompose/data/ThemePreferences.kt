package br.edu.satc.todolistcompose.data

import android.content.Context
import android.content.SharedPreferences

enum class ThemeMode {
    LIGHT, DARK, AUTO
}

class ThemePreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

    fun getThemeMode(): ThemeMode {
        val value = prefs.getString("theme_mode", ThemeMode.AUTO.name)
        return try {
            ThemeMode.valueOf(value ?: ThemeMode.AUTO.name)
        } catch (e: IllegalArgumentException) {
            ThemeMode.AUTO
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        prefs.edit().putString("theme_mode", mode.name).apply()
    }
}
