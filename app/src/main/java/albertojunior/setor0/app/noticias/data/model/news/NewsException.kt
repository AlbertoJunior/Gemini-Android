package albertojunior.setor0.app.noticias.data.model.news

class NewsException(message: String, exception: Throwable? = null) : Exception(message, exception) {
    companion object {
        fun newsIsNull(exception: Throwable? = null) = NewsException("News is null", exception)
        fun conversionFailure(exception: Throwable? = null) = NewsException("Conversion failure", exception)
    }
}