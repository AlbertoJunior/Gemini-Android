package albertojunior.setor0.app.data.model.news

import albertojunior.setor0.app.core.data.District

object NewsDistrict {
    fun newsMostCommon(district: District) = when (district) {
        District.ALFIRAN -> "guerras, brigas, produção de armas"
        District.AMEISEN -> "trabalho, fábricas, androides sexuais, prostituição"
        District.PTITSY -> "inovações tecnológicas, como acessórios, proteses, dispositivos"
        District.ARANHAS -> "perseguições, cargas roubadas, casas de aposta"
        District.TOKOJIRAMI -> "mafias brigando por território, comercio drogas, armas, peças proibidas"
        District.VYURA -> "pesquisas científicas, desaparecimento"
        else -> ""
    }

    fun newsInfo(district: District) = "${district.name}, fica na região ${district.region.name}, " +
            "os acontecimentos normalmente são sobre ${newsMostCommon(district)}; " +
            "Se refira a este como ${district.refer}; " +
            "Seus vizinhos são os bairros ${district.neighborLeft} e ${district.neighborRight};" +
            newsNoHave(district)

    private fun newsNoHave(district: District) =
        if (district.noHave.isEmpty()) "" else " Não falar de ${district.noHave}"
}