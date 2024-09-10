package albertojunior.setor0.app.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class District(
    val name: String,
    val region: Region,
    val noHave: String,
    val refer: String,
    val neighborLeft: String,
    val neighborRight: String,
) : Parcelable {

    companion object {
        val ALFIRAN = District(
            "Alfiran",
            Region.OESTE,
            "Megacorporações",
            "em Alfiran ou de Alfiran",
            "Ptitsy",
            "Tokojirami"
        )
        val AMEISEN = District(
            "Ameisen",
            Region.SUDESTE,
            "Milícias",
            "em Ameisen ou de Ameisen",
            "Aranhas",
            "Vyura"
        )
        val PTITSY = District(
            "Ptitsy",
            Region.NORTE,
            "Usinas de energia",
            "em Ptitsy ou de Ptitsy",
            "Vyura",
            "Alfiran"
        )
        val ARANHAS = District(
            "Aranhas",
            Region.SUL,
            "Usinas de energia",
            "nos Aranhas ou dos Aranhas",
            "Tokojirami",
            "Ameisen"
        )
        val TOKOJIRAMI = District(
            "Tokojirami",
            Region.SUDOESTE,
            "Notícias sobre desenvolvimento tecnológico",
            "em Tokojirami ou de Tokojirami",
            "Alfiran",
            "Aranhas"
        )
        val VYURA = District(
            "Vyura",
            Region.NORDESTE,
            "Usinas de energia",
            "em Vyura ou de Vyura",
            "Ameisen",
            "Ptitsy"
        )
        val districts = listOf(
            ALFIRAN,
            AMEISEN,
            ARANHAS,
            PTITSY,
            TOKOJIRAMI,
            VYURA,
        )
    }
}