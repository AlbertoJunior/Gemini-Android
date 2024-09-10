package albertojunior.setor0.app.design.compose.theme.icons

import albertojunior.setor0.app.design.compose.theme.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

val Icons.News: Painter
    @Composable get() {
        return cachedIcons ?: painterResource(Icons.Id.news).also { cachedIcons }
    }

private var cachedIcons: Painter? = null