package com.demo.util;

@SuppressWarnings("unused")
public final class LogUtil {

    private LogUtil() {
    }

    /**
     * 将string裁剪到指定的长度，超过长度限制的部分使用"..."代替
     *
     * @param str    字符串
     * @param maxLen 最大长度
     * @return 裁剪后的字符串
     */
    public static String truncateStr(String str, int maxLen) {

        if (str != null) {
            if (str.length() <= maxLen) {
                return str;
            } else {
                return str.substring(0, maxLen - 3) + "...";
            }
        }

        return null;
    }

    /**
     * 返回隐藏部分信息的userId
     */
    public static String hideUserId(String userId) {

        if (StringUtils.isNotEmpty(userId) && !userId.equals("-") && userId.length() == 16) {
            String start = userId.substring(0, 6);
            String end = userId.substring(11, 16);
            userId = start + "*****" + end;
        }

        return userId;
    }

    /**
     * 对参数进行格式化，使用方法
     * <blockquote><pre>
     * String param1 = "OK";
     * Object param2 = "param2 value";
     * LogUtil.formatParam("param1", param1, "param2", param2);
     * </pre></blockquote>
     * 此时返回结果为 "param1: OK; param2: param2 value"<br>
     * <br>
     * 要求param成对出现，如果配对失败，比如
     * <blockquote><pre>
     * Object param1 = "param1 value";
     * Object param2 = "param2 value";
     * LogUtil.formatParam("param1", param1, "param2");
     * </pre></blockquote>
     * 最后一个参数会被忽略，并不会抛出异常，此时打印结果为"param1: param1 value"<br>
     * 如果传入的参数值为null，则会输出"-"
     * 比如
     * <blockquote><pre>
     * Object obj1 = null;
     * LogUtil.formatParam("obj1", obj1);
     * </pre></blockquote>
     * 输出结果为"obj1: -"<br>
     * 如果只输入一个null作为参数，则返回"", 不会出现异常
     *
     * @return 格式化后的参数列表
     */
    public static String formatParam(Object... args) {
        if (args == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        final int N = args.length / 2;

        for (int i = 0; i < N; ++i) {
            sb.append(args[2 * i]).append(":");

            Object val = args[2 * i + 1];
            if (val == null) {
                sb.append("-");
            } else {
                sb.append(val);
            }

            if (i != N - 1) {
                sb.append(";");
            }
        }

        return sb.toString();
    }
}
