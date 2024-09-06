package albertojunior.setor0.app.ai.prompter

class PromptWelcome : Prompter {
    override fun mount(): String {
        return "Crie uma mensagem de boas vindas para ser apresentado em um aplicativo que mostra notícias normalmente ruins e problemáticas do Setor 0."
    }
}