package albertojunior.setor0.app.noticias.data.model.features

import albertojunior.setor0.app.noticias.ui.features.IdNavigation
import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat

typealias RNav = albertojunior.setor0.app.noticias.R.id

sealed class Feature(
    val title: String,
    val description: String,
    val navigation: IdNavigation,
    @DrawableRes val icon: Int,
    val hideNav: Boolean = false
) {

    fun toFeatureView(resources: Resources) = FeatureView(
        title,
        description,
        navigation,
        ResourcesCompat.getDrawable(resources, icon, null),
        hideNav
    )

    companion object {
        val values = listOf(
            Establishment,
            News
        )
    }

    private data object Establishment : Feature(
        "Estabelecimentos",
        "Gerencie seus estabelecimentos",
        IdNavigation(RNav.navigation_establishment),
        albertojunior.setor0.app.establishment.R.drawable.ic_lab_panel_black_24dp,
        true
    )

    private data object News : Feature(
        "Notícias",
        "Fique por dentro das últimas notícias do Setor 0",
        IdNavigation(RNav.navigation_news),
        albertojunior.setor0.app.noticias.R.drawable.ic_newspaper_black_24dp,
    )
}