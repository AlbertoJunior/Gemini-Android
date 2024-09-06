package albertojunior.setor0.app.ai.prompter

import albertojunior.setor0.app.data.model.news.NewsDTO
import com.google.gson.Gson

object PrefabsPrompt {
    private val prefabJsonNews = Gson().toJson(NewsDTO("", ""))

    private const val OBRIGATORY_INFORMATION =
        "Informações obrigatórias para a notícia:\n" +
                "Não existem animais neste mundo então não use referências e analogias a eles, a não ser ratos ou insetos como baratas e formigas.\n" +
                "Não coloque data.\n" +
                "Formate o texto da notícia apenas com quebra de linhas com \\n.\n" +
                "Não existe nenhum cargo político ou autoridades.\n" +
                "Não existe força policial, tudo é resolvido pelos próprios habitantes e milícias.\n" +
                "Não existem países, estados ou outras cidades.\n" +
                "Existe apenas a cidade onde aconteceu a notícia, que se chama  Colmeia.\n" +
                "Apenas mencione que ocorreu, em %s que é um bairro localizado no Setor 0, uma região mais externa da Colmeia.\n" +
                "Evite repetições de palavras e termos.\n" +
                "Não use o termo cibernéticos troque por Aprimoramentos.\n" +
                "Não use o termo cyberpunk.\n"

    private const val OPTIONAL_INFORMATION =
        "Informações opcionais para enriquecer a notícia:\n" +
                "Megacorporações: Se for condizente, mencione a(s) megacorporação(ões) envolvida(s) no incidente e sua influência na Colmeia.\n" +
                "Tecnologia: Descreva tecnologias cyberpunk relevantes para o incidente (Aprimoramentos, IA, realidade virtual, vida na Rede, etc.).\n" +
                "Distritos: Especifique em qual distrito o incidente ocorreu e como isso afeta a dinâmica da cidade.\n" +
                "Milícias: Explique o papel das milícias no conflito.\n" +
                "Morfologias: Os seres que existem no mundo são Androides, Ciborgues, Sintéticos e Humanos.\n" +
                "Clima: O ambiente é sempre seco e quente, nunca chove.\n" +
                "Nomes: [Nomes com estilo cyberpunk].\n" +
                "Linguagem: Use girias e linguagem coloquial."

    val PREFAB_CHANNEL_PROMPT =
        OBRIGATORY_INFORMATION + OPTIONAL_INFORMATION + "As informações sobre este bairro são as seguintes: %s. "

    val PREFAB_CHANNEL_OLD_NEWS_PROMPT =
        "$PREFAB_CHANNEL_PROMPT " + "As informações sobre a última notícia: %s."
}