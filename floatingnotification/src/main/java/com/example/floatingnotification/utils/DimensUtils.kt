package com.example.floatingnotification.utils

import android.content.Context

class DimensUtils {
    companion object {
        fun convertDpToPixel(dps: Int, context: Context): Int {
            val scale = context.resources.displayMetrics.density
            return (dps * scale + 0.5f).toInt()
        }

        fun convertDpToPixel(dps: Float, context: Context): Int {
            val scale = context.resources.displayMetrics.density
            return (dps * scale + 0.5f).toInt()
        }

        fun convertPixelsToDp(px: Int, context: Context): Int {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return (px / metrics.density).toInt()
        }
    }
}