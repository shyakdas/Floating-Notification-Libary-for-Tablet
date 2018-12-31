package com.example.floatingnotification;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constants {


    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";
    public static final String CONFLICT = "conflict";

    @StringDef({SUCCESS, FAILED, CONFLICT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessageType {
    }

    public static final int SUCCESS_INT = 101;
    public static final int FAILED_INT = 102;
    public static final int CONFLICT_INT = 103;
    public static final int SINGLE = 104;
    public static final int MULTIPLE = 103;
}
