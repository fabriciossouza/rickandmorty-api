package com.github.fabriciossouza.rickandmortyapi.core.util;

import static java.lang.Integer.parseInt;

public final class StringUtil {

    public static Integer getNumberByString(String str) {
        Integer number = null;
        if(str != null) {
            String strReplace = getNumber(str);
            return  strReplace != null ? parseInt(strReplace) : null;
        }

        return number;
    }

    public static String getNumber(String str) {
        return str != null ? str.replaceAll("[^0-9]", "") : null;
    }
}
