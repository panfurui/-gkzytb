package com.demo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class BizLogger {
    private static final int MAX_PARAM_OUT_LEN = 300;

    private static Logger logger = null;

    private BizLogger(Logger logger) {
        this.logger = logger;
    }

    public static BizLogger getLogger(Class clazz) {
        return new BizLogger(LoggerFactory.getLogger(clazz));
    }

    public static BizLogger getLogger(String name) {
        return new BizLogger(LoggerFactory.getLogger(name));
    }


    public String getName() {
        return logger.getName();
    }

    /**
     * Is the logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for the DEBUG level,
     * false otherwise.
     */
    public static boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * Log a message at the DEBUG level.
     *
     * @param msg the message string to be logged
     */
    public static void debug(String methodName, String msg) {
        if (isDebugEnabled()) {
            logger.debug(concatMsg(methodName, msg));
        }
    }


    /**
     * Log a message at the DEBUG level according to the specified format
     * and argument.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the DEBUG level. </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void debug(String methodName, String format, Object arg) {
        if (isDebugEnabled()) {
            logger.debug(addToFormat(methodName, format), arg);
        }
    }

    /**
     * Log a message at the DEBUG level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the DEBUG level. </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void debug(String methodName, String format, Object arg1, Object arg2) {
        if (isDebugEnabled()) {
            logger.debug(addToFormat(methodName, format), arg1, arg2);
        }
    }

    /**
     * Log a message at the DEBUG level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the DEBUG level. </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void debug(String methodName, String format, Object... argArray) {
        if (isDebugEnabled()) {
            logger.debug(addToFormat(methodName, format), argArray);
        }
    }

    /**
     * Log an exception (throwable) at the DEBUG level with an
     * accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to logger
     */
    public void debug(String methodName, String msg, Throwable t) {
        if (isDebugEnabled()) {
            logger.debug(concatMsg(methodName, msg), t);
        }
    }

    /**
     * Is the logger instance enabled for the INFO level?
     *
     * @return True if this Logger is enabled for the INFO level,
     * false otherwise.
     */
    public static boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * Log a message at the INFO level.
     *
     * @param msg the message string to be logged
     */
    public static void info(String msg) {
        if (isInfoEnabled()) {
            logger.info(msg);
        }
    }

    /**
     * Log a message at the INFO level.
     *
     * @param msg the message string to be logged
     */
    public static void info(String methodName, String msg) {
        if (isInfoEnabled()) {
            logger.info(concatMsg(methodName, msg));
        }
    }

    /**
     * Log a message at the INFO level according to the specified format
     * and argument.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the INFO level. </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void info(String methodName, String format, Object arg) {
        if (isInfoEnabled()) {
            logger.info(addToFormat(methodName, format), arg);
        }
    }

    /**
     * Log a message at the INFO level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the INFO level. </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void info(String methodName, String format, Object arg1, Object arg2) {
        if (isInfoEnabled()) {
            logger.info(addToFormat(methodName, format), arg1, arg2);
        }
    }

    /**
     * Log a message at the INFO level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the INFO level. </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public static void info(String methodName, String format, Object... argArray) {
        if (isInfoEnabled()) {
            logger.info(addToFormat(methodName, format), argArray);
        }
    }

    /**
     * Log an exception (throwable) at the INFO level with an
     * accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to logger
     */
    public static void info(String methodName, String msg, Throwable t) {
        if (isInfoEnabled()) {
            logger.info(concatMsg(methodName, msg), t);
        }
    }

    /**
     * Is the logger instance enabled for the WARN level?
     *
     * @return True if this Logger is enabled for the WARN level,
     * false otherwise.
     */
    public static boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    /**
     * Log a message at the WARN level.
     *
     * @param msg the message string to be logged
     */
    public static void warn(String methodName, String msg) {
        if (isWarnEnabled()) {
            logger.warn(concatMsg(methodName, msg));
        }
    }

    /**
     * Log a message at the WARN level.
     *
     * @param msg the message string to be logged
     */
    public static void warn(String msg) {
        if (isWarnEnabled()) {
            logger.warn(msg);
        }
    }

    /**
     * Log a message at the WARN level according to the specified format
     * and argument.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the WARN level. </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void warn(String methodName, String format, Object arg) {
        if (isWarnEnabled()) {
            logger.warn(addToFormat(methodName, format), arg);
        }
    }

    /**
     * Log a message at the WARN level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the WARN level. </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void warn(String methodName, String format, Object... argArray) {
        if (isWarnEnabled()) {
            logger.warn(addToFormat(methodName, format), argArray);
        }
    }

    /**
     * Log a message at the WARN level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the WARN level. </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void warn(String methodName, String format, Object arg1, Object arg2) {
        if (isWarnEnabled()) {
            logger.warn(addToFormat(methodName, format), arg1, arg2);
        }
    }

    /**
     * Log an exception (throwable) at the WARN level with an
     * accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to logger
     */
    public void warn(String methodName, String msg, Throwable t) {
        if (isWarnEnabled()) {
            logger.warn(concatMsg(methodName, msg), t);
        }
    }

    /**
     * Is the logger instance enabled for the ERROR level?
     *
     * @return True if this Logger is enabled for the ERROR level,
     * false otherwise.
     */
    public static boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    /**
     * Log a message at the ERROR level.
     *
     * @param msg the message string to be logged
     */
    public static void error(String msg) {
        if (isErrorEnabled()) {
            logger.error(msg);
        }
    }

    /**
     * Log a message at the ERROR level.
     *
     * @param msg the message string to be logged
     */
    public static void error(String methodName, String msg) {
        if (isErrorEnabled()) {
            logger.error(concatMsg(methodName, msg));
        }
    }

    /**
     * Log a message at the ERROR level according to the specified format
     * and argument.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the ERROR level. </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void error(String methodName, String format, Object arg) {
        if (isErrorEnabled()) {
            logger.error(addToFormat(methodName, format), arg);
        }
    }

    /**
     * Log a message at the ERROR level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the ERROR level. </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void error(String methodName, String format, Object arg1, Object arg2) {
        if (isErrorEnabled()) {
            logger.error(addToFormat(methodName, format), arg1, arg2);
        }
    }

    /**
     * Log a message at the ERROR level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the ERROR level. </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public static void error(String methodName, String format, Object... argArray) {
        if (isErrorEnabled()) {
            logger.error(addToFormat(methodName, format), argArray);
        }
    }

    /**
     * Log an exception (throwable) at the ERROR level with an
     * accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to logger
     */
    public static void error(String methodName, String msg, Throwable t) {
        if (isErrorEnabled()) {
            logger.error(concatMsg(methodName, msg), t);
        }
    }

    /**
     * 函数入口打印，使用info级别
     *
     * @param methodName 函数名
     * @param paramIn    处理后的入参
     */
    public static void infoFunEnter(String methodName, Object paramIn) {
        if (isInfoEnabled()) {
            logger.info("【{}】 method_start, 入参：{}", methodName, paramIn);
        }
    }

    /**
     * 打印info级别的函数出口日志
     *
     * @param methodName 函数名
     * @param paramIn    入参
     * @param paramOut   出参
     */
    public static void infoFunExit(String methodName, Object paramIn, Object paramOut) {
        if (isInfoEnabled()) {
            String strParamOut = null;
            if (paramOut != null) {
                strParamOut = LogUtil.truncateStr(paramOut.toString(), MAX_PARAM_OUT_LEN);
            }

            logger.info("【{}】 method_exit, 入参: {}, 出参: {}", methodName, paramIn, strParamOut);
        }
    }

    /**
     * 打印info级别的函数出口日志
     *
     * @param methodName 函数名
     * @param paramIn    入参
     * @param paramOut   出参
     * @param msg        额外的消息
     */
    public static void infoFunExit(String methodName, Object paramIn, Object paramOut, String msg) {
        if (isInfoEnabled()) {
            String strParamOut = null;
            if (paramOut != null) {
                strParamOut = LogUtil.truncateStr(paramOut.toString(), MAX_PARAM_OUT_LEN);
            }

            msg = (msg == null) ? "" : msg;
            logger.info("【{}】 method_exit, 入参: {}, 出参: {}. {}", methodName, paramIn, strParamOut, msg);
        }
    }

    /**
     * 打印info级别的函数出口日志
     *
     * @param methodName 函数名
     * @param paramIn    入参
     * @param paramOut   出参
     * @param format     格式化字符串
     * @param arg1       the first argument
     */
    public static void infoFunExit(String methodName, Object paramIn, Object paramOut, String format, Object arg1) {
        if (isInfoEnabled()) {
            String strParamOut = null;
            if (paramOut != null) {
                strParamOut = LogUtil.truncateStr(paramOut.toString(), MAX_PARAM_OUT_LEN);
            }

            logger.info("【{}】 method_exit, 入参: {}, 出参: {}." + format, methodName, paramIn, strParamOut, arg1);
        }
    }


    /**
     * 打印info级别的函数出口日志
     *
     * @param methodName 函数名
     * @param paramIn    入参
     * @param paramOut   出参
     * @param format     格式化字符串
     * @param arg1       the first argument
     * @param arg2       the second argument
     */
    public static void infoFunExit(String methodName, Object paramIn, Object paramOut, String format, Object arg1, Object arg2) {
        if (isInfoEnabled()) {
            String strParamOut = null;
            if (paramOut != null) {
                strParamOut = LogUtil.truncateStr(paramOut.toString(), MAX_PARAM_OUT_LEN);
            }

            logger.info("【{}】 method_exit, 入参: {}, 出参: {}." + format, methodName, paramIn, strParamOut, arg1, arg2);
        }
    }

    /**
     * 打印info级别的函数出口日志
     *
     * @param methodName 函数名
     * @param paramIn    入参
     * @param paramOut   出参
     * @param format     格式化字符串
     * @param argArray   the arg array
     */
    public static void infoFunExit(String methodName, Object paramIn, Object paramOut, String format, Object... argArray) {
        if (isInfoEnabled()) {
            String strParamOut = null;
            if (paramOut != null) {
                strParamOut = LogUtil.truncateStr(paramOut.toString(), MAX_PARAM_OUT_LEN);
            }

            logger.info("【{}】 method_exit, 入参: {}, 出参: {}." + format, methodName, paramIn, strParamOut, argArray);
        }
    }

    /**
     * 函数入口打印，使用debug级别
     *
     * @param methodName 函数名
     * @param paramIn    处理后的入参
     */
    public static void debugFunEnter(String methodName, Object paramIn) {
        if (isDebugEnabled()) {
            logger.debug("【{}】 method_start, 入参：{}", methodName, paramIn);
        }
    }

    private static String addToFormat(String methodName, String format) {
        return String.format("【%s】 %s", methodName, format);
    }

    private static String concatMsg(String methodName, String msg) {
        return String.format("【%s】 %s", methodName, msg);
    }

}
