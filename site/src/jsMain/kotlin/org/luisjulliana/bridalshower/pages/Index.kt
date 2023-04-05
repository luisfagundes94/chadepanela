package org.luisjulliana.bridalshower.pages

import androidx.compose.runtime.*
import com.luisjulliana.bridalshower.components.layouts.PageLayout
import com.varabyte.kobweb.compose.css.backgroundImage
import com.varabyte.kobweb.compose.css.width
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Img

private const val IMAGE_URL =
    "url('https://images.pexels.com/photos/1482803/pexels-photo-1482803.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2')"

@Page
@Composable
fun HomePage() {
    PageLayout("") {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .styleModifier {
                    width(100.percent)
                    backgroundImage(IMAGE_URL)
                    backgroundSize("contain")
                    backgroundRepeat("no-repeat")
                    backgroundPosition("top")
                }
        ) {

        }
    }
}
