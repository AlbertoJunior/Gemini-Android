package albertojunior.setor0.app.establishment.data.model

import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic
import albertojunior.setor0.app.establishment.data.enums.EstablishmentTypes

sealed class District(
    val name: String,
) {
    abstract fun typesBySize(size: Int): List<EstablishmentTypes>

    open fun commonCharacteristics(): List<EstablishmentCharacteristic> = emptyList()

    open fun commonGoodTraits(): List<EstablishmentTraits.Good> = emptyList()

    open fun commonBadTraits(): List<EstablishmentTraits.Bad> = emptyList()

    fun getRandomizedPreferredCharacteristic() = commonCharacteristics().shuffled().take(1)[0]

    override fun toString() = name

    object ALFIRAN : District("Alfiran") {
        override fun commonCharacteristics() =
            listOf(
                EstablishmentCharacteristic.SEGURANCA,
                EstablishmentCharacteristic.SERVICOS
            )

        override fun typesBySize(size: Int) =
            when (size) {
                1 -> listOf(
                    EstablishmentTypes.CONTAINER_PROSTITUICAO
                )

                2 -> listOf(
                    EstablishmentTypes.FABRICA_ARMAS,
                    EstablishmentTypes.LOJA_ARMAS
                )

                3 -> listOf(
                    EstablishmentTypes.MEDTEC,
                    EstablishmentTypes.LOJA_ARMAS
                )

                4 -> listOf(
                    EstablishmentTypes.FABRICA_ARMAS_GRANDE,
                    EstablishmentTypes.CELULA_ENERGIA,
                    EstablishmentTypes.LOJA_ARMAS
                )

                5 -> listOf(
                    EstablishmentTypes.USINA_ENERGIA,
                    EstablishmentTypes.LOJA_ARMAS
                )

                else -> emptyList()
            }

        override fun commonGoodTraits() =
            listOf(
                EstablishmentTraits.Good.BemEquipado,
                EstablishmentTraits.Good.BemEnergizado,
                EstablishmentTraits.Good.SegurancaMelhorada,
            )

        override fun commonBadTraits() =
            listOf(
                EstablishmentTraits.Bad.CriminalidadeAlta,
                EstablishmentTraits.Bad.DependenciaImportacoes,
                EstablishmentTraits.Bad.InfraestruturaPrecaria
            )
    }

    object AMEISEN : District("Ameisen") {
        override fun commonCharacteristics() =
            listOf(
                EstablishmentCharacteristic.CLIENTES,
                EstablishmentCharacteristic.SERVICOS,
                EstablishmentCharacteristic.INFRAESTRUTURA,
            )

        override fun typesBySize(size: Int) =
            when (size) {
                1 -> listOf(
                    EstablishmentTypes.CONTAINER_PROSTITUICAO
                )

                2 -> listOf(
                    EstablishmentTypes.CASA_PROSTITUICAO
                )

                3 -> listOf(
                    EstablishmentTypes.CASA_PROSTITUICAO_LUXO
                )

                4 -> listOf(
                    EstablishmentTypes.FABRICA_ANDROIDES,
                    EstablishmentTypes.CELULA_ENERGIA,
                    EstablishmentTypes.CASA_PROSTITUICAO_LUXO
                )

                5 -> listOf(
                    EstablishmentTypes.USINA_ENERGIA,
                    EstablishmentTypes.CASA_PROSTITUICAO_LUXO
                )

                else -> emptyList()
            }

        override fun commonGoodTraits() =
            listOf(
                EstablishmentTraits.Good.FonteTesouro,
                EstablishmentTraits.Good.BemEnergizado,
                EstablishmentTraits.Good.FacilidadeAcessoInformacoes,
            )

        override fun commonBadTraits() =
            listOf(
                EstablishmentTraits.Bad.Insatisfacao,
                EstablishmentTraits.Bad.MaFama,
                EstablishmentTraits.Bad.DificilDefender
            )
    }

    object ARANHAS : District("Aranhas") {
        override fun commonCharacteristics() =
            listOf(
                EstablishmentCharacteristic.CLIENTES,
                EstablishmentCharacteristic.SERVICOS,
                EstablishmentCharacteristic.ESTABELECIMENTOS,
            )

        override fun typesBySize(size: Int) =
            when (size) {
                1 -> listOf(
                    EstablishmentTypes.CONTAINER_PROSTITUICAO
                )

                2 -> listOf(
                    EstablishmentTypes.LOJA_CARROS,
                    EstablishmentTypes.LOJA_ARMAS,
                    EstablishmentTypes.CASSINO_PEQUENO
                )

                3 -> listOf(
                    EstablishmentTypes.LOJA_GRANDE,
                    EstablishmentTypes.LOJA_ARMAS,
                    EstablishmentTypes.CASSINO_PEQUENO
                )

                4 -> listOf(
                    EstablishmentTypes.LOJA_CARROS_LUXO,
                    EstablishmentTypes.CASSINO,
                    EstablishmentTypes.GALPAO,
                    EstablishmentTypes.GALPAO_GRANDE
                )

                5 -> listOf(
                    EstablishmentTypes.LOJA_CARROS_GRANDE,
                    EstablishmentTypes.GALPAO_GRANDE
                )

                else -> emptyList()
            }

        override fun commonGoodTraits() =
            listOf(
                EstablishmentTraits.Good.Autogestao,
                EstablishmentTraits.Good.FonteTesouro,
                EstablishmentTraits.Good.FacilidadeAcessoInformacoes,
            )

        override fun commonBadTraits() =
            listOf(
                EstablishmentTraits.Bad.CriminalidadeAlta,
                EstablishmentTraits.Bad.BlackoutsConstantes,
                EstablishmentTraits.Bad.Impostos
            )
    }

    object PTITSY : District("Ptitsy") {
        override fun commonCharacteristics() =
            listOf(
                EstablishmentCharacteristic.ESTABELECIMENTOS,
                EstablishmentCharacteristic.CIBERSEGURANCA,
                EstablishmentCharacteristic.INFRAESTRUTURA,
            )

        override fun typesBySize(size: Int) =
            when (size) {
                1 -> listOf(
                    EstablishmentTypes.LABORATORIO,
                    EstablishmentTypes.MEDTEC
                )

                2 -> listOf(
                    EstablishmentTypes.LABORATORIO,
                    EstablishmentTypes.MEDTEC,
                    EstablishmentTypes.CASSINO_PEQUENO
                )

                3 -> listOf(
                    EstablishmentTypes.LABORATORIO,
                    EstablishmentTypes.LABORATORIO_GRANDE,
                    EstablishmentTypes.MEDTEC
                )

                4 -> listOf(
                    EstablishmentTypes.LABORATORIO_GRANDE
                )

                5 -> listOf(
                    EstablishmentTypes.LABORATORIO_GRANDE
                )

                else -> emptyList()
            }

        override fun commonGoodTraits() =
            listOf(
                EstablishmentTraits.Good.CibersegurancaMelhorada,
                EstablishmentTraits.Good.FacilidadeAcessoItens,
                EstablishmentTraits.Good.FacilidadeAcessoInformacoes,
            )

        override fun commonBadTraits() =
            listOf(
                EstablishmentTraits.Bad.DependenciaImportacoes,
                EstablishmentTraits.Bad.ProblemasDificeisResolver
            )
    }

    object TOKOJIRAMI : District("Tokojirami") {
        override fun commonCharacteristics() =
            listOf(
                EstablishmentCharacteristic.CLIENTES,
                EstablishmentCharacteristic.ESTABELECIMENTOS,
                EstablishmentCharacteristic.SEGURANCA
            )

        override fun typesBySize(size: Int) =
            when (size) {
                1 -> listOf(
                    EstablishmentTypes.MEDTEC,
                    EstablishmentTypes.LABORATORIO,
                    EstablishmentTypes.CONTAINER_PROSTITUICAO
                )

                2 -> listOf(
                    EstablishmentTypes.LABORATORIO,
                    EstablishmentTypes.CASA_PROSTITUICAO,
                    EstablishmentTypes.LOJA_ARMAS,
                    EstablishmentTypes.CASSINO_PEQUENO
                )

                3 -> listOf(
                    EstablishmentTypes.FERREIRO,
                    EstablishmentTypes.CASA_PROSTITUICAO,
                    EstablishmentTypes.CASA_CARNE,
                    EstablishmentTypes.LOJA_GRANDE,
                    EstablishmentTypes.LOJA_ARMAS
                )

                4 -> listOf(
                    EstablishmentTypes.FERREIRO,
                    EstablishmentTypes.GALPAO,
                    EstablishmentTypes.GALPAO_GRANDE
                )

                5 -> listOf(
                    EstablishmentTypes.GALPAO_GRANDE
                )

                else -> emptyList()
            }

        override fun commonGoodTraits() =
            listOf(
                EstablishmentTraits.Good.IncentivosGovernamentais,
                EstablishmentTraits.Good.SegurancaMelhorada,
                EstablishmentTraits.Good.FacilidadeAcessoItens,
            )

        override fun commonBadTraits() =
            listOf(
                EstablishmentTraits.Bad.CriminalidadeAlta,
                EstablishmentTraits.Bad.ControleGovernamental,
                EstablishmentTraits.Bad.Impostos
            )
    }

    object VYURA : District("Vyura") {
        override fun commonCharacteristics() =
            listOf(
                EstablishmentCharacteristic.INFRAESTRUTURA,
                EstablishmentCharacteristic.SEGURANCA,
                EstablishmentCharacteristic.CIBERSEGURANCA
            )

        override fun typesBySize(size: Int) =
            when (size) {
                1 -> listOf(
                    EstablishmentTypes.LABORATORIO,
                    EstablishmentTypes.MEDTEC,
                    EstablishmentTypes.CENTRO_PESQUISA_PEQUENO,
                )

                2 -> listOf(
                    EstablishmentTypes.LABORATORIO,
                    EstablishmentTypes.MEDTEC,
                    EstablishmentTypes.CENTRO_PESQUISA_PEQUENO,
                )

                3 -> listOf(
                    EstablishmentTypes.LABORATORIO,
                    EstablishmentTypes.MEDTEC,
                    EstablishmentTypes.CENTRO_PESQUISA_PEQUENO,
                    EstablishmentTypes.CENTRO_PESQUISA_MEDIO,
                )

                4 -> listOf(
                    EstablishmentTypes.LABORATORIO,
                    EstablishmentTypes.MEDTEC,
                    EstablishmentTypes.CENTRO_PESQUISA_MEDIO,
                    EstablishmentTypes.CENTRO_PESQUISA_GRANDE,
                )

                5 -> listOf(
                    EstablishmentTypes.LABORATORIO,
                    EstablishmentTypes.MEDTEC,
                    EstablishmentTypes.CENTRO_PESQUISA_GRANDE,
                    EstablishmentTypes.FABRICA_APRIMORAMENTOS,
                    EstablishmentTypes.FABRICA_SINTETICOS
                )

                else -> emptyList()
            }

        override fun commonGoodTraits() =
            listOf(
                EstablishmentTraits.Good.IncentivosGovernamentais,
                EstablishmentTraits.Good.BemFeito,
                EstablishmentTraits.Good.BemEquipado,
            )

        override fun commonBadTraits() =
            listOf(
                EstablishmentTraits.Bad.ControleGovernamental,
                EstablishmentTraits.Bad.Insatisfacao,
                EstablishmentTraits.Bad.ProgressoLento
            )
    }
}