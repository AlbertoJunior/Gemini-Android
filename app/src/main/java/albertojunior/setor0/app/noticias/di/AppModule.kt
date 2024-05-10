package albertojunior.setor0.app.noticias.di

import albertojunior.setor0.app.noticias.BuildConfig
import albertojunior.setor0.app.noticias.model.NewsDTO
import albertojunior.setor0.app.noticias.ui.news.NewsRepository
import albertojunior.setor0.app.noticias.ui.news.NewsRepositoryImpl
import android.content.Context
import android.content.res.Resources
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.TextPart
import com.google.ai.client.generativeai.type.generationConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val generationConfig = generationConfig {
        topP = 0.7F // a porcentagem da soma de palavras deve ser variado
        temperature = 0.8F // precisa ser criativo
        maxOutputTokens = 6000 // muitos tokens por se tratar de uma noticia
    }
    private val safetySettingsNoneSafety = listOf(
        SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.NONE),
        SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.NONE),
        SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.NONE),
        SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.ONLY_HIGH),
    )
    private val journalSystem = Content(
        "Jornalista",
        listOf(
            TextPart(
                "Você é um jornalista em um mundo cyberpunk. Escreva uma notícia, com no máximo 4 parágrafos e no mínimo 2."
            ),
            TextPart(
                "Responda em formato JSON. Siga o modelo:" + Gson().toJson(NewsDTO("", ""))
            )
        )
    )

    @Provides
    @Singleton
    @Named("Default")
    fun provideGenerativeAi(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-pro",
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = generationConfig,
            safetySettings = safetySettingsNoneSafety,
        )
    }

    @Provides
    @Singleton
    @Named("Pro")
    fun provideGenerativeProAi(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-1.5-pro-latest",
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = generationConfig,
            safetySettings = safetySettingsNoneSafety,
            systemInstruction = journalSystem
        )
    }

    @Provides
    @Singleton
    fun provideNotificationsRepository(): NewsRepository {
        return NewsRepositoryImpl()
    }

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): Resources {
        return context.resources
    }

}