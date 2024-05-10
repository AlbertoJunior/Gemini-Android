package albertojunior.setor0.app.noticias.model.prompter

import albertojunior.setor0.app.noticias.model.news.NewsDTO

class PromptCoreInformationNews(
    private val news: NewsDTO
) : Prompter {
    private val basePrompt = "Separe as principais informações da notícia a seguir, de forma concisa, " +
            "sinalizando Quem causou, O que causou, Como causou e Consequências.\n"

    override fun mount(): String {
        return basePrompt +
                "Título: ${news.title}\n" +
                "Notícia: ${news.news}"
    }
}