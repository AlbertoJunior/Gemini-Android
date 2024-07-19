package albertojunior.setor0.app.noticias.utils

import albertojunior.setor0.app.noticias.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible

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

    fun generateDefaultFadeIn(context: Context): Animation =
        AnimationUtils.loadAnimation(context, R.anim.fade_in).apply {
            duration = 600
        }

    fun generateDefaultFadeOut(context: Context): Animation =
        AnimationUtils.loadAnimation(context, R.anim.fade_out).apply {
            duration = 600
        }

    fun animateFadeInOut(
        context: Context,
        finalValue: Boolean,
        view: View
    ) {
        val animation = if (finalValue) {
            R.anim.fade_in
        } else {
            R.anim.fade_out
        }

        AnimationUtils.loadAnimation(context, animation).apply {
            duration = 300

            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    view.isVisible = finalValue
                }

                override fun onAnimationRepeat(p0: Animation?) {
                }
            })
            view.startAnimation(this)
        }
    }
}