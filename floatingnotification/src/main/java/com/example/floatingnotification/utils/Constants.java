package com.example.floatingnotification.utils;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constants {


    public static final String SUCCESS = "notification_success";
    public static final String FAILED = "notification_failed";
    public static final String CONFLICT = "notification_conflict";
    public static final int SUCCESS_INT = 101;
    public static final int FAILED_INT = 102;
    public static final int CONFLICT_INT = 103;
    public static final int SINGLE = 104;
    public static final int MULTIPLE = 103;
    @StringDef({SUCCESS, FAILED, CONFLICT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessageType {
    }
}
