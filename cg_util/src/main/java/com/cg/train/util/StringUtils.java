package com.cg.train.util;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @className StringUtils
 * @Description 字符串工具类
 * @Author craig
 * @Date 2022/10/15 11:30
 * @Version 1.0
 */
public class StringUtils {
    /**
     * 判断字符串是否超过最大长度
     * @param rawStr
     * @param maxLen
     * @return
     */
    public static String verifyMaxLen(String rawStr, int maxLen) {
        if (isNullOrEmpty(rawStr)) {
            return rawStr;
        }
        if (rawStr.length() > maxLen) {
            return rawStr.substring(0, maxLen);
        }
        return rawStr;
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return true：空，false：非空
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    /**
     * 是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return str.matches("\\d*");
    }

    /**
     * 字符串转整型，非整型转默认值0
     * @param str
     * @return
     */
    public static int parseInt(String str) {
        if (isNullOrEmpty(str) || !isNumber(str)) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    /**
     * 获取字符串长度，中文计算两个长度
     * @param str
     * @return
     */
    public static int getStringLen(String str) {
        int len = 0;
        if (isNullOrEmpty(str)) {
            return len;
        }
        str = str.trim();
        char[] arr = str.toCharArray();
        for (char c : arr) {
            if (isChinese(c)) {
                len += 2;
            } else {
                len++;
            }
        }
        return len;
    }

    /**
     * 判断字符是不是中文
     * @param c
     * @return
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    /**
     * 连接字符串, 以分隔符分隔
     * @param collection
     * @param delimiter
     * @return
     */
    public static String connectStr(Collection<? extends Object> collection, String delimiter){
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        return collection.stream().map(Object::toString).collect(Collectors.joining(delimiter));
    }
}
