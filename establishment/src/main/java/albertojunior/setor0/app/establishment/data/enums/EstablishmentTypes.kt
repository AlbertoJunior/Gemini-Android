package albertojunior.setor0.app.establishment.data.enums

import albertojunior.setor0.app.establishment.data.model.EstablishmentTraits

enum class EstablishmentTypes(
    val description: String,
    val preferredGoodTraits: List<EstablishmentTraits.Good> = emptyList()
) {
    BAR("Bar"),
    BOATE("Boate"),
    BOATE_GRANDE("Boate grande"),
    CASA_APOSTAS("Casa de apostas"),
    CASA_CARNE("Casa de carnes"),
    CASA_PROSTITUICAO("Casa de prostituição"),
    CASA_PROSTITUICAO_LUXO(
        "Casa de prostituição de luxo",
        listOf(EstablishmentTraits.Good.FonteTesouro)
    ),
    CASSINO(
        "Cassino",
        listOf(
            EstablishmentTraits.Good.FonteTesouro,
            EstablishmentTraits.Good.SegurancaMelhorada,
            EstablishmentTraits.Good.CibersegurancaMelhorada
        )
    ),
    CASSINO_PEQUENO("Cassino pequeno"),
    CONTAINER_PROSTITUICAO("Container de prostituição"),
    CELULA_ENERGIA(
        "Célula de energia",
        listOf(EstablishmentTraits.Good.BemEnergizado)
    ),
    CENTRO_PESQUISA_PEQUENO(
        "Centro de pesquisa pequeno",
        listOf(EstablishmentTraits.Good.BemEquipado, EstablishmentTraits.Good.BemFeito)
    ),
    CENTRO_PESQUISA_MEDIO(
        "Centro de pesquisa médio",
        listOf(EstablishmentTraits.Good.BemEquipado, EstablishmentTraits.Good.BemFeito)
    ),
    CENTRO_PESQUISA_GRANDE(
        "Centro de pesquisa grande",
        listOf(EstablishmentTraits.Good.BemEquipado, EstablishmentTraits.Good.BemFeito)
    ),
    FABRICA(
        "Fábrica",
        listOf(EstablishmentTraits.Good.BemEquipado, EstablishmentTraits.Good.BemEnergizado)
    ),
    FABRICA_APRIMORAMENTOS(
        "Fábrica de Aprimoramentos",
        listOf(EstablishmentTraits.Good.BemEquipado, EstablishmentTraits.Good.BemEnergizado)
    ),
    FABRICA_ANDROIDES(
        "Fábrica de Androides",
        listOf(EstablishmentTraits.Good.BemEquipado, EstablishmentTraits.Good.BemEnergizado)
    ),
    FABRICA_SINTETICOS(
        "Fábrica de Sintéticos",
        listOf(EstablishmentTraits.Good.BemEquipado, EstablishmentTraits.Good.BemEnergizado)
    ),
    FABRICA_ARMAS(
        "Fábrica de armas de projeção",
        listOf(
            EstablishmentTraits.Good.BemEquipado,
            EstablishmentTraits.Good.BemEnergizado,
            EstablishmentTraits.Good.SegurancaMelhorada
        )
    ),
    FABRICA_ARMAS_GRANDE(
        "Fábrica de armas de projeção grande",
        listOf(
            EstablishmentTraits.Good.BemEquipado,
            EstablishmentTraits.Good.BemEnergizado,
            EstablishmentTraits.Good.SegurancaMelhorada
        )
    ),
    FABRICA_GRANDE(
        "Fábrica grande",
        listOf(
            EstablishmentTraits.Good.BemEquipado,
            EstablishmentTraits.Good.BemEnergizado,
            EstablishmentTraits.Good.SegurancaMelhorada
        )
    ),
    FAVELA("Favela"),
    FERREIRO("Ferreiro"),
    GALPAO("Galpão"),
    GALPAO_GRANDE("Galpão grande"),
    GALPAO_PEQUENO("Galpão pequeno"),
    LABORATORIO(
        "Laboratório",
        listOf(EstablishmentTraits.Good.BemEquipado)
    ),
    LABORATORIO_GRANDE(
        "Laboratório grande",
        listOf(EstablishmentTraits.Good.BemEquipado)
    ),
    LOJA("Loja"),
    LOJA_ARMAS("Loja de armas"),
    LOJA_GRANDE(
        "Loja grande",
        listOf(EstablishmentTraits.Good.FonteTesouro)
    ),
    LOJA_PEQUENA("Loja pequena"),
    LOJA_CARROS("Loja de carros pequena"),
    LOJA_CARROS_GRANDE("Loja de carros grande"),
    LOJA_CARROS_LUXO(
        "Loja de carros de luxo",
        listOf(EstablishmentTraits.Good.FonteTesouro)
    ),
    MEDTEC(
        "MedTec",
        listOf(EstablishmentTraits.Good.BemEquipado)
    ),
    OFICINA(
        "Oficina",
        listOf(EstablishmentTraits.Good.BemEquipado)
    ),
    OFICINA_GRANDE(
        "Oficina grande",
        listOf(EstablishmentTraits.Good.BemEquipado)
    ),
    OFICINA_PEQUENA(
        "Oficina Pequena",
        listOf(EstablishmentTraits.Good.BemEquipado)
    ),
    RADIO(
        "Rádio",
        listOf(EstablishmentTraits.Good.BemEnergizado)
    ),
    RESTAURANTE("Restaurante"),
    USINA_ENERGIA(
        "Usina de Energia Nuclear",
        listOf(EstablishmentTraits.Good.BemEnergizado, EstablishmentTraits.Good.FonteTesouro)
    ),
    ;
}