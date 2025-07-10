package albertojunior.setor0.app.utils

import albertojunior.setor0.app.design.R
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible

object ContextUtils {
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