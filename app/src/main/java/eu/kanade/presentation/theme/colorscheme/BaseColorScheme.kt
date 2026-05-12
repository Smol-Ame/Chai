package eu.kanade.presentation.theme.colorscheme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

internal abstract class BaseColorScheme {

    abstract val darkScheme: ColorScheme
    abstract val lightScheme: ColorScheme

    // Chai --> Cannot be pure black as there's content scrolling behind it, but I'ma do it anyways
    // https://m3.material.io/components/navigation-bar/guidelines#90615a71-607e-485e-9e09-778bfc080563
    private val surfaceContainer = Color(0xFF000000)
    private val surfaceContainerHigh = Color(0xFF000000)
    private val surfaceContainerHighest = Color(0xFF000000)
    // Chai <--

    fun getColorScheme(
        isDark: Boolean,
        isAmoled: Boolean,
        overrideDarkSurfaceContainers: Boolean,
    ): ColorScheme {
        if (!isDark) return lightScheme

        if (!isAmoled) return darkScheme

        val amoledScheme = darkScheme.copy(
            background = Color.Black,
            onBackground = Color.White,
            surface = Color.Black,
            onSurface = Color.White,
        )

        if (!overrideDarkSurfaceContainers) return amoledScheme

        return amoledScheme.copy(
            surfaceVariant = surfaceContainer, // Navigation bar background (ThemePrefWidget)
            surfaceContainerLowest = surfaceContainer,
            surfaceContainerLow = surfaceContainer,
            surfaceContainer = surfaceContainer, // Navigation bar background
            surfaceContainerHigh = surfaceContainerHigh,
            surfaceContainerHighest = surfaceContainerHighest,
        )
    }
}
