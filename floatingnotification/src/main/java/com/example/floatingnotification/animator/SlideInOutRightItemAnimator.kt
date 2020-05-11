package com.example.floatingnotification.animator

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class SlideInOutRightItemAnimator(recyclerView: RecyclerView) : BaseItemAnimator(recyclerView) {

    override fun animateRemoveImpl(holder: ViewHolder?) {
        val view = holder?.itemView
        val animation = ViewCompat.animate(view!!)
        mRemoveAnimations.add(holder)
        animation.setDuration(200L)
                .alpha(0f)
                .translationX(mRecyclerView?.layoutManager?.width?.toFloat() as Float)
                .setListener(object : VpaListenerAdapter() {
                    override fun onAnimationStart(view: View) {
                        dispatchRemoveStarting(holder)
                    }

                    override fun onAnimationEnd(view: View) {
                        animation.setListener(null)
                        view.alpha = 1f
                        view.translationX = mRecyclerView?.layoutManager?.width?.toFloat() as Float
                        dispatchRemoveFinished(holder)
                        mRemoveAnimations.remove(holder)
                        dispatchFinishedWhenDone()
                    }
                }).start()
    }

    override fun prepareAnimateAdd(holder: ViewHolder?) {
        holder?.itemView?.translationX = mRecyclerView?.layoutManager?.width?.toFloat() as Float
    }

    override fun animateAddImpl(holder: ViewHolder?) {
        val view = holder?.itemView
        val animation = ViewCompat.animate(view!!)
        mAddAnimations.add(holder)
        animation.translationX(0f)
                .alpha(1f)
                .setDuration(500L)
                .setListener(object : VpaListenerAdapter() {
                    override fun onAnimationStart(view: View) {
                        dispatchAddStarting(holder)
                    }

                    override fun onAnimationCancel(view: View) {
                        view.translationX = 0f
                        view.alpha = 1f
                    }

                    override fun onAnimationEnd(view: View) {
                        animation.setListener(null)
                        dispatchAddFinished(holder)
                        view.translationX = 0f
                        view.alpha = 1f
                        mAddAnimations.remove(holder)
                        dispatchFinishedWhenDone()
                    }
                }).start()
    }
}