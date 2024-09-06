package albertojunior.setor0.app.establishment.utils

import albertojunior.setor0.app.establishment.data.model.Establishment
import albertojunior.setor0.app.establishment.data.repository.DistrictRepository
import albertojunior.setor0.app.establishment.use_case.EstablishmentGeneratorUseCase
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import java.io.File
import java.nio.file.Files
import java.util.GregorianCalendar

object FilesUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateFiles() {
        for (i in 0..10) {
            val establishment = EstablishmentGeneratorUseCase.instance(
                RandomUtils.randomize(6, 1),
                DistrictRepository.getAllDistricts().random()
            )
            val json = GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(Establishment.EstablishmentCleaned.fromEstablishment(establishment))

            try {
                DistrictRepository.getAllDistricts().forEach {
                    File("establishments", it.name).mkdirs()
                }

                val file =
                    File(
                        "establishments/${establishment.bairro}",
                        "${establishment.bairro}_${establishment.tamanho}_${GregorianCalendar().timeInMillis}.json"
                    )
                Files.write(file.toPath(), json.toByteArray())
            } catch (ignore: Exception) {
                ignore.printStackTrace()
                println("Arquivo não criado")
            }
            println(json)
        }
        println("terminou de gerar")
    }
}