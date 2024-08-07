package albertojunior.setor0.app.establishment.data.model

data class Characteristic(
    val characteristic: EstablishmentCharacteristic,
    val level: Int
) {
    override fun toString() = "${characteristic.description}: $level"
}