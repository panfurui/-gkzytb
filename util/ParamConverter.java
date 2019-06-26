package com.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamConverter {
    public static String convertToLikeParam(String originParam) {
        if (StringUtils.isEmpty(originParam)) {
            return null;
        }
        String fourBackslash = "\u005C\u005C\u005C\u005C";
        String singleBackslash = "\u005C\u005C";
        originParam = originParam.replaceAll(Pattern.quote(singleBackslash), Matcher.quoteReplacement(fourBackslash));
        String escapedPercent = "\u005C\u005C\u0025";
        originParam = originParam.replaceAll("%", Matcher.quoteReplacement(escapedPercent));
        String dash = "\u002D";
        originParam = originParam.replaceAll(Pattern.quote(dash), Matcher.quoteReplacement("\u005c\u005c\u002D"));
        originParam = "%" + originParam + "%";
        return originParam;
    }

}
