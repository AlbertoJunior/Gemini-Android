# Objetivo
Repositório destinado ao estudo do [Gemini](https://aistudio.google.com/) em uma aplicação Android.

# Imersão Inteligência Artificial 2ª Edição Alura
Obtive acesso a biblioteca um pouco antes do curso de [imersão da Alura](https://cursos.alura.com.br/imersao), mas foi durante ele que consegui aprofundar os conhecimentos necessários para otimizar meu código e a forma como utilizo a IA Generativa.

## Contexto
Este aplicativo serve para criar notícias em um mundo distópico para o jogo de RPG [Setor 0 - O Submundo](https://www.setor0rpg.com.br).

As notícias são criadas a partir do ponto de vista de um "jornalista" habitante do Setor 0, de modo informal e coloquial.

Por se tratar de um mundo devastado, as configurações de segurança foram reduzidas ou praticamente zeradas, mas podem ser configuradas no seguinte trecho:
```kotlin
private val safetySettingsNoneSafety = listOf(
    SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.NONE),
    SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.NONE),
    SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.NONE),
    SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.ONLY_HIGH),
)
```

O Consumo da API é através do objeto `GenerativeModel`, que é criado na execução do aplicativo e é o mesmo para todas as notícias, mantendo assim um padrão de `Temperature`, `topK` e `topP`.
```kotlin
@Provides
@Singleton
fun provideGenerativeAi(): GenerativeModel {
    return GenerativeModel(
        modelName = "gemini-pro",
        apiKey = BuildConfig.GEMINI_API_KEY,
        generationConfig = generationConfig,
        safetySettings = safetySettingsNoneSafety,
    )
}
```

A chave da API é configurada no arquivo `local.defaults.properties` alterando o valor `DEFAULT_API_KEY` pela sua chave, que pode ser obtida [aqui](https://aistudio.google.com/app/apikey).
```md
GEMINI_API_KEY=DEFAULT_API_KEY
```
Caso deseje utilizar essa configuração de modo mais seguro e que possa disponibilizar para versionamento de código, veja como na documentação do [Google](https://developers.google.com/maps/documentation/android-sdk/secrets-gradle-plugin?hl=pt-br#kotlin).

## Arquitetura
* MVVM (Model-View-ViewModel) - é um padrão de arquitetura de software que visa separar a lógica de negócios e a apresentação dos dados da interface do usuário (UI). A ideia é facilitar o desenvolvimento, teste e manutenção de aplicações, principalmente em interfaces complexas. ([Gerado com o Gemini](https://aistudio.google.com/app/prompts/new_chat))

## Bibliotecas
* IA Generativa [Gemini](https://ai.google.dev/gemini-api/docs/get-started/android?hl=pt-br)
* Cores e Componentes visuais com [Material Design](https://m3.material.io/develop/android/mdc-android)
* Utilização de KEYs de forma segura com [Secrets](https://developers.google.com/maps/documentation/android-sdk/secrets-gradle-plugin?hl=pt-br#kotlin)
* Injeção de Dependências com [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=pt-br)
* Apresentação de dados reativos com [LiveData](https://developer.android.com/topic/libraries/architecture/livedata?hl=pt-br)
* Conversão de dados com [Gson](https://github.com/google/gson)

## Funcionalidades
* Gerador de notícias do Setor 0.
* Copiar notícias.

## Funcionalidades Pretendidas
* [x] Gerador de notícias.
* [x] Copiar notícias.
* [ ] Comentários nas notícias.
* [ ] Continuação de notícias passadas.
* [ ] Pontos de vista para uma mesma notícia.
* [ ] Inserir uma "notícia" através de comentários e ver a análise do jornalista.