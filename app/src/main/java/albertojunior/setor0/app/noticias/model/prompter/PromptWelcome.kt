package albertojunior.setor0.app.noticias.model.prompter

class PromptWelcome : Prompter {
    override fun mount(): String {
        return "Deseje uma mensagem de boas vindas para ser apresentado em um aplicativo que mostra notícias normalmente ruins."
    }
}