package albertojunior.setor0.app.establishment.data.model

import albertojunior.setor0.app.establishment.data.enums.EstablishmentCharacteristic

data class Characteristic(
    val characteristic: EstablishmentCharacteristic,
    val level: Int
) {
    override fun toString() = "${characteristic.description}: $level"
}