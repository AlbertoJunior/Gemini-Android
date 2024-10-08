package albertojunior.setor0.app.data.model.news

class NewsException(message: String, exception: Throwable? = null) : Exception(message, exception) {
    companion object {
        fun newsIsNull(exception: Throwable? = null) = NewsException("News is null", exception)
        fun conversionFailure(exception: Throwable? = null) = NewsException("Conversion failure", exception)
    }
}