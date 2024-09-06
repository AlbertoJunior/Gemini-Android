package albertojunior.setor0.app.establishment.utils

object Extensions {
    fun <T> takeShuffledByList(list: List<T>) = list.shuffled().take(1)[0]
}
