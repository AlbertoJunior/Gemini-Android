package albertojunior.setor0.app.establishment.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object ContextUtils {
    fun copyMessage(context: Context, textCopied: String, text: String?) {
        val textToCopy = text.orEmpty()

        if (textToCopy.isEmpty())
            return

        // Obtenha o gerenciador de área de transferência
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        // Cria um ClipData para guardar o texto
        val clipData = ClipData.newPlainText(textCopied, textToCopy)

        // Define o ClipData na área de transferência
        clipboardManager.setPrimaryClip(clipData)
    }
}