package com.pmis.user.service.utils;

import java.util.List;

public class Utils {
    public static boolean ifEmptyList(List<?> list) {
        if (null == list || list.isEmpty()) {
            return true;
        }
        return false;
    }
}
