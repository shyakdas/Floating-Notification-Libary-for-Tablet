package com.example.floatingnotification.animator

import android.view.View
import androidx.core.view.ViewPropertyAnimatorListener

open class VpaListenerAdapter : ViewPropertyAnimatorListener {
    override fun onAnimationStart(view: View) {}
    override fun onAnimationEnd(view: View) {}
    override fun onAnimationCancel(view: View) {}
}