package albertojunior.setor0.app.establishment.data.model

import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic
import java.util.UUID

internal sealed class EstablishmentTraits(
    val name: String,
    val generatedId: String
) {

    abstract fun onlyOne(): Boolean

    abstract fun copy(): EstablishmentTraits

    open fun prerequisites(): List<EstablishmentTraits> = emptyList()

    override fun toString() = name

    abstract class Good(name: String, id: String = UUID.randomUUID().toString()) : EstablishmentTraits(name, id) {
        override fun copy(): EstablishmentTraits {
            return object : Good(name) {
                override fun enableTrait() = this@Good.enableTrait()

                override fun preferredCharacteristics() = this@Good.preferredCharacteristics()

                override fun onlyOne() = this@Good.onlyOne()
            }
        }

        open fun preferredCharacteristics(): List<EstablishmentCharacteristic> = emptyList()

        open fun enableTrait(): List<Good> = emptyList()

        override fun onlyOne() = true

        fun getRandomizedPreferredCharacteristic() = preferredCharacteristics().shuffled().take(1)[0]

        object Autogestao : Good("Autogestão") {
            override fun preferredCharacteristics() = listOf(EstablishmentCharacteristic.SERVICOS)
        }

        object BemEnergizado : Good("Bem Energizado") {
            override fun preferredCharacteristics() = listOf(EstablishmentCharacteristic.SERVICOS)
            override fun enableTrait() = listOf(PossuiTerminal)
        }

        object BemEquipado : Good("Bem Equipado") {
            override fun preferredCharacteristics() = listOf(EstablishmentCharacteristic.SERVICOS)
            override fun enableTrait() = listOf(ProducaoConstante)
        }

        object BemFeito : Good("Bem Feito") {
            override fun preferredCharacteristics() =
                listOf(
                    EstablishmentCharacteristic.SERVICOS,
                    EstablishmentCharacteristic.INFRAESTRUTURA
                )

            override fun onlyOne() = false
        }

        object CibersegurancaMelhorada : Good("Cibersegurança Melhorada") {
            override fun preferredCharacteristics() = listOf(EstablishmentCharacteristic.CIBERSEGURANCA)
        }

        object Influencia : Good("Influência") {
            override fun preferredCharacteristics() = listOf(EstablishmentCharacteristic.CIBERSEGURANCA)
        }

        object IncentivosGovernamentais : Good("Incentivos Governamentais") {
            override fun preferredCharacteristics() =
                listOf(
                    EstablishmentCharacteristic.SEGURANCA,
                    EstablishmentCharacteristic.CIBERSEGURANCA,
                    EstablishmentCharacteristic.CLIENTES
                )

            override fun onlyOne() = false
        }

        object FacilidadeAcessoInformacoes : Good("Facilidade de Acesso a Informações") {
            override fun preferredCharacteristics() = listOf(EstablishmentCharacteristic.SERVICOS)
        }

        object FacilidadeAcessoItens : Good("Facilidade de Acesso a Itens") {
            override fun preferredCharacteristics() =
                listOf(
                    EstablishmentCharacteristic.SERVICOS,
                    EstablishmentCharacteristic.ESTABELECIMENTOS
                )

            override fun onlyOne() = false
        }

        object FonteTesouro : Good("Fonte de Tesouro") {
            override fun preferredCharacteristics() =
                listOf(
                    EstablishmentCharacteristic.CLIENTES,
                    EstablishmentCharacteristic.SERVICOS
                )
        }

        object PossuiTerminal : Good("Possui um Terminal") {
            override fun preferredCharacteristics() =
                listOf(
                    EstablishmentCharacteristic.INFRAESTRUTURA,
                    EstablishmentCharacteristic.CIBERSEGURANCA
                )

            override fun prerequisites() = listOf(BemEnergizado)
        }

        object ProducaoConstante : Good("Produção Constante") {
            override fun preferredCharacteristics() =
                listOf(
                    EstablishmentCharacteristic.SERVICOS,
                    EstablishmentCharacteristic.SEGURANCA
                )

            override fun prerequisites() = listOf(BemEquipado)
            override fun onlyOne() = false
        }

        object SegurancaMelhorada : Good("Segurança Melhorada") {
            override fun preferredCharacteristics() = listOf(EstablishmentCharacteristic.SEGURANCA)
        }

        companion object {
            val traits = listOf(
                Autogestao,
                BemEnergizado,
                BemEquipado,
                BemFeito,
                CibersegurancaMelhorada,
                Influencia,
                IncentivosGovernamentais,
                FacilidadeAcessoInformacoes,
                FacilidadeAcessoItens,
                FonteTesouro,
                PossuiTerminal,
                ProducaoConstante,
                SegurancaMelhorada
            )
        }
    }

    abstract class Bad(name: String, id: String = UUID.randomUUID().toString()) : EstablishmentTraits(name, id) {
        override fun copy(): EstablishmentTraits {
            return object : Bad(name) {
                override fun onlyOne() = this@Bad.onlyOne()
                override fun prerequisites() = this@Bad.prerequisites()
            }
        }

        override fun onlyOne() = false

        object BlackoutsConstantes : Bad("Blackouts Constantes")

        object ControleGovernamental : Bad("Controle Governamental")

        object CriminalidadeAlta : Bad("Criminalidade Alta")

        object DependenciaImportacoes : Bad("Dependência de Importações") {
            fun getValue(size: Int) = when (size) {
                1 -> 1
                2 -> 3
                3 -> 4
                4 -> 7
                5 -> 8
                6 -> 12
                else -> 0
            }
        }

        object Desperdicio : Bad("Desperdício") {
            override fun prerequisites(): List<EstablishmentTraits> = listOf(Good.ProducaoConstante)
        }

        object DificilDefender : Bad("Difícil de Defender")

        object Impostos : Bad("Impostos") {
            fun getValue(size: Int) = when (size) {
                1 -> 1
                2 -> 3
                3 -> 4
                4 -> 7
                5 -> 8
                6 -> 12
                else -> 0
            }
        }

        object InfraestruturaPrecaria : Bad("Infraestrutura Precária")
        object Insatisfacao : Bad("Insatisfação")
        object MaFama : Bad("Má Fama")
        object ProblemasDificeisResolver : Bad("Problemas Difíceis de Resolver")
        object ProgressoLento : Bad("Progresso Lento")
        object SegurancaFragilizada : Bad("Segurança Fragilizada") {
            override fun onlyOne() = true
        }

        companion object {
            val traits = listOf(
                BlackoutsConstantes,
                ControleGovernamental,
                CriminalidadeAlta,
                DependenciaImportacoes,
                Desperdicio,
                DificilDefender,
                Impostos,
                InfraestruturaPrecaria,
                Insatisfacao,
                MaFama,
                ProblemasDificeisResolver,
                ProgressoLento,
                SegurancaFragilizada,
            )
        }
    }
}