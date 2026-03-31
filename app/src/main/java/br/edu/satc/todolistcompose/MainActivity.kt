package br.edu.satc.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.edu.satc.todolistcompose.data.AppDatabase
import br.edu.satc.todolistcompose.data.ThemeMode
import br.edu.satc.todolistcompose.data.ThemePreferences
import br.edu.satc.todolistcompose.ui.screens.HomeScreen
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(this)
        val taskDao = db.taskDao()
        val themePreferences = ThemePreferences(this)

        setContent {
            var themeMode by remember { mutableStateOf(themePreferences.getThemeMode()) }
            val isSystemDark = isSystemInDarkTheme()

            val isDark = when (themeMode) {
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
                ThemeMode.AUTO -> isSystemDark
            }

            ToDoListComposeTheme(darkTheme = isDark) {
                HomeScreen(
                    taskDao = taskDao,
                    themeMode = themeMode,
                    onThemeModeChange = { newMode ->
                        themeMode = newMode
                        themePreferences.setThemeMode(newMode)
                    }
                )
            }
        }
    }
}
