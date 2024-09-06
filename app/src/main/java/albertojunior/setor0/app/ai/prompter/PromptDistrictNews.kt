package albertojunior.setor0.app.ai.prompter

import albertojunior.setor0.app.data.model.District
import albertojunior.setor0.app.data.model.news.News

class PromptDistrictNews(
    private val district: District,
    private val lastNews: List<News>
) : Prompter {
    override fun mount(): String {
        return if (lastNews.isNotEmpty()) {
            older(district, lastNews.last())
        } else {
            newer()
        }
    }

    private fun newer() =
        String.format(PrefabsPrompt.PREFAB_CHANNEL_PROMPT, district.name, district.information)

    private fun older(
        district: District,
        news: News
    ) = String.format(
        PrefabsPrompt.PREFAB_CHANNEL_OLD_NEWS_PROMPT,
        district.name,
        district.information,
        news.coreInformation
    )
}