package albertojunior.setor0.app.noticias.model

data class District(
    val name: String,
    val region: Region,
    val newsMostCommon: String,
    val noHave: String,
    val refer: String,
    val neighborLeft: String,
    val neighborRight: String,
) {

    companion object {
        val alfiran = District(
            "Alfiran",
            Region.OESTE,
            "guerras, brigas, produção de armas",
            "Megacorporações",
            "em Alfiran ou de Alfiran",
            "Ptitsy",
            "Tokojirami"
        )
        val ameisen = District(
            "Ameisen",
            Region.SUDESTE,
            "trabalho, fábricas, androides sexuais, prostituição",
            "Milícias",
            "em Ameisen ou de Ameisen",
            "Aranhas",
            "Vyura"
        )
        val ptitsy = District(
            "Ptitsy",
            Region.NORTE,
            "inovações tecnológicas, como acessórios, proteses, dispositivos",
            "Usinas de energia",
            "em Ptitsy ou de Ptitsy",
            "Vyura",
            "Alfiran"
        )
        val aranhas = District(
            "Aranhas",
            Region.SUL,
            "perseguições, cargas roubadas, casas de aposta",
            "Usinas de energia",
            "nos Aranhas ou dos Aranhas",
            "Tokojirami",
            "Ameisen"
        )
        val tokojirami = District(
            "Tokojirami",
            Region.SUDOESTE,
            "mafias brigando por território, comercio drogas, armas, peças proibidas",
            "Notícias sobre desenvolvimento tecnológico",
            "em Tokojirami ou de Tokojirami",
            "Alfiran",
            "Aranhas"
        )
        val vyura = District(
            "Vyura",
            Region.NORDESTE,
            "pesquisas científicas, desaparecimento",
            "Usinas de energia",
            "em Vyura ou de Vyura",
            "Ameisen",
            "Ptitsy"
        )
        val districts = listOf(
            alfiran,
            ameisen,
            aranhas,
            ptitsy,
            tokojirami,
            vyura,
        )
    }

    private val noHave2 = if (noHave.isEmpty()) "" else " Não falar de $noHave"

    val information =
        "$name, fica na região ${region.name}, os acontecimentos normalmente são sobre $newsMostCommon; " +
                "Se refira a este como $refer; " +
                "Seus vizinhos são os bairros $neighborLeft e $neighborRight;" +
                noHave2
}