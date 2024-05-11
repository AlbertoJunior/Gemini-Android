package albertojunior.setor0.app.noticias.ai

import albertojunior.setor0.app.noticias.data.model.coreinformation.CoreInformationNews
import albertojunior.setor0.app.noticias.data.model.news.NewsDTO
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.TextPart
import com.google.ai.client.generativeai.type.generationConfig
import com.google.gson.Gson

object GenerativeModelConfig {
    object Config {
        val GENERATION = generationConfig {
            topK = 30
            topP = 0.7F // a porcentagem da soma de palavras deve ser variado
            temperature = 0.8F // precisa ser criativo
            maxOutputTokens = 8192 // muitos tokens por se tratar de uma noticia
        }

        val ANALYST = generationConfig {
            topK = 15
            topP = 0.4F // a porcentagem da soma de palavras deve ser variado
            temperature = 0.2F // não precisa ser criativo
            maxOutputTokens = 1024 // poucos tokens por se tratar de uma noticia
        }

        val WELCOME = generationConfig {
            topK = 30
            topP = 0.5F
            temperature = 0.8F // precisa ser criativo
            maxOutputTokens = 1024 // poucos tokens para uma mensagem de boas vindas é suficiente
        }
    }

    object SafetySettings {
        val LOWEST = listOf(
            SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.NONE),
            SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.NONE),
            SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.NONE),
            SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.ONLY_HIGH),
        )
    }

    object SystemInstruction {
        val NEWS = Content(
            "Jornalista",
            listOf(
                TextPart(
                    "Você é um jornalista em um mundo cyberpunk."
                ),
                TextPart(
                    "Escreva uma notícia, com no máximo 4 parágrafos e no mínimo 2."
                ),
                TextPart(
                    "Responda em formato JSON. Siga o modelo:" + Gson().toJson(NewsDTO("", ""))
                )
            )
        )

        val ANALYST = Content(
            "Analista",
            listOf(
                TextPart(
                    "Responda em formato JSON. Siga o modelo:" + Gson().toJson(CoreInformationNews())
                ),
                TextPart(
                    "Não use mais que 200 palavras"
                )
            )
        )


        val WELCOME = Content(
            "Bem-vindo",
            listOf(
                TextPart("Você é uma pessoa super otimista que vive em um mundo cyberpunk"),
                TextPart("Deve ter menos de 50 palavras"),
                TextPart("O Mundo que você vive se chama Setor 0"),
                TextPart("Nunca utilize termos como cibercidadão, ou cyberpunk."),
            )
        )
    }
}