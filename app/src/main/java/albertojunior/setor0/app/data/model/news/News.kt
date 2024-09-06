package albertojunior.setor0.app.data.model.news

import albertojunior.setor0.app.data.model.District
import albertojunior.setor0.app.data.model.coreinformation.CoreInformationNews
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val district: District,
    val title: String,
    val body: String,
    val coreInformation: CoreInformationNews,
    val beforeNews: News? = null
) : Parcelable