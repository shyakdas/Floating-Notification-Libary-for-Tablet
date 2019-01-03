package com.example.floatingnotification.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DimensUtils {

    public static int convertDpToPixel(int dps, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        return pixels;
    }

    public static int convertDpToPixel(float dps, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        return pixels;
    }

    public static int convertPixelsToDp(int px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int dp = (int) (px / metrics.density);
        return dp;
    }
}