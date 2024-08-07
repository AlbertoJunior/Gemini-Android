import java.math.BigInteger
import java.security.SecureRandom
import java.util.*

object RandomUtils {
    private val seed = BigInteger(1, SecureRandom().generateSeed(64))
    private val random = Random(seed.toLong())

    /**
     * Este método retorna um valor aleatório entre 0 e maxValue somado ao bonus
     *
     * @param maxValue o último número possível
     * @param bonus    o bonus que será incrementado ao final do número sorteado
     * @return retorna um valor aleatório entre 0 e maxValue somado ao bonus
     */
    fun randomize(maxValue: Int, bonus: Int = 0): Int = random.nextInt(maxValue) + bonus
}