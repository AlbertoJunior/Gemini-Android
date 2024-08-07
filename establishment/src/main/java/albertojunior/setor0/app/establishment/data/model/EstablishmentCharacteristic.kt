package albertojunior.setor0.app.establishment.data.model

enum class EstablishmentCharacteristic(val description: String) {
    CIBERSEGURANCA("Cibersegurança"),
    CLIENTES("Clientes"),
    ESTABELECIMENTOS("Estabelecimentos"),
    INFRAESTRUTURA("Infraestrutura"),
    SEGURANCA("Segurança"),
    SERVICOS("Serviços"), ;

    companion object {
        private fun getAll() = EstablishmentCharacteristic.entries.toTypedArray()

        fun obtainCharacteristics(size: Int): List<EstablishmentCharacteristic> {
            if (size == 1)
                return listOf(SERVICOS)

            return mutableListOf(SERVICOS).apply {
                val shuffledList = getAll()
                    .filter { it != SERVICOS }
                    .shuffled()
                    .take(size - 1)
                addAll(shuffledList)
            }
        }
    }
}