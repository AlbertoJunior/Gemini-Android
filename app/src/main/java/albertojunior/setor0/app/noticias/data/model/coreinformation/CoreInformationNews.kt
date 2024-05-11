package albertojunior.setor0.app.noticias.data.model.coreinformation

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoreInformationNews(
    val who: String = "",
    val what: String = "",
    @SerializedName("when", alternate = ["wheen"]) val wheen: String = "",
    val how: String = "",
    val results: String = ""
) : Parcelable {
    override fun toString(): String {
        return "Quem: $who\n" +
                "O que: $what\n" +
                "Quando: $wheen\n" +
                "Como: $how\n" +
                "Consequências: $results"
    }
}