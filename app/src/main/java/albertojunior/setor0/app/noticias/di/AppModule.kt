package albertojunior.setor0.app.noticias.di

import albertojunior.setor0.app.noticias.BuildConfig
import albertojunior.setor0.app.noticias.ai.GenerativeModelConfig
import albertojunior.setor0.app.noticias.data.repository.NewsRepository
import albertojunior.setor0.app.noticias.data.repository.NewsRepositoryImpl
import android.content.Context
import android.content.res.Resources
import com.google.ai.client.generativeai.GenerativeModel
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
    @Provides
    @Singleton
    @Named("Default")
    fun provideGenerativeAi(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-pro",
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = GenerativeModelConfig.Config.GENERATION,
            safetySettings = GenerativeModelConfig.SafetySettings.LOWEST,
        )
    }

    @Provides
    @Singleton
    @Named("Pro")
    fun provideGenerativeProAi(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-1.5-pro-latest",
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = GenerativeModelConfig.Config.GENERATION,
            safetySettings = GenerativeModelConfig.SafetySettings.LOWEST,
            systemInstruction = GenerativeModelConfig.SystemInstruction.NEWS
        )
    }

    @Provides
    @Singleton
    @Named("News-Analyst")
    fun provideAnalystAi(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-1.5-pro-latest",
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = GenerativeModelConfig.Config.ANALYST,
            systemInstruction = GenerativeModelConfig.SystemInstruction.ANALYST
        )
    }

    @Provides
    @Singleton
    @Named("Welcome")
    fun provideGenerativeWelcomeAi(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-1.5-pro-latest",
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = GenerativeModelConfig.Config.WELCOME,
            safetySettings = GenerativeModelConfig.SafetySettings.LOWEST,
            systemInstruction = GenerativeModelConfig.SystemInstruction.WELCOME
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