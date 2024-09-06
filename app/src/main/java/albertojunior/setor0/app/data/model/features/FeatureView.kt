package albertojunior.setor0.app.data.model.features

import albertojunior.setor0.app.ui.features.IdNavigation
import android.graphics.drawable.Drawable

data class FeatureView(
    val title: String,
    val description: String,
    val navigation: IdNavigation,
    val icon: Drawable?,
    val hideNav: Boolean,
)