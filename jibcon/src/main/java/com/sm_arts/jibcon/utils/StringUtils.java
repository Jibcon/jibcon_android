package com.sm_arts.jibcon.utils;

import io.reactivex.annotations.Nullable;

/**
 * Created by jaeyoung on 8/12/17.
 */

public class StringUtils {
    public static boolean contains(@Nullable String s1, @Nullable String s2) {
        if (s1 == null) {
            return false;
        } else {
            return s1.contains(s2);
        }
    }
}
