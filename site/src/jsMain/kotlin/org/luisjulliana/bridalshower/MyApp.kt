package org.luisjulliana.bridalshower

import androidx.compose.runtime.Composable
import com.luisjulliana.bridalshower.di.appModule
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerBaseStyle
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.rgb
import org.jetbrains.compose.web.css.vh
import org.koin.core.context.startKoin

@InitSilk
fun updateTheme(ctx: InitSilkContext) {
    ctx.config.initialColorMode = ColorMode.LIGHT

    ctx.stylesheet.registerBaseStyle("body") {
        Modifier.fontFamily("Roboto", "Handlee")
    }

    ctx.stylesheet.registerBaseStyle("div") {
        val lighterBlack = rgb(30, 30, 30)
        Modifier.color(lighterBlack)
    }
}

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    setUpKoin()
    SilkApp {
        Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
            content()
        }
    }
}

private fun setUpKoin() {
    startKoin {
        modules(
            appModule
        )
    }
}
