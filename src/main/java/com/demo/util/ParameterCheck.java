package com.demo.util;

import java.util.Collection;
import java.util.Date;

/**
 * @author sx-9129 2016/10/17/0017
 * 参数检查的工具类
 */
public class ParameterCheck {
    /**
     * @param obj 需要检查的参数
     * @return null返回true，notnull返回false
     * @author sx-9129 2016/10/17
     * 检查参数是否为空
     */
    public static Boolean checkNull(Object obj) {
        if (obj == null)
            return true;
        else
            return false;
    }

    public static Boolean checkNotNull(Object obj) {
        return !checkNull(obj);
    }

    /**
     * @param objs 需要检查的参数列表
     * @return 参数列表里只要有一个参数为空，则返回true。都不为空，返回false
     * @author sx-9129 2016/10/17
     * 检查多个参数是否为空
     */
    public static Boolean checkNulls(Object... objs) {
        for (Object obj : objs) {
            if (checkNull(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param collection 要检查的集合对象
     * @return null或者empty返回true，否则返回
     * @author sx-9129 2016/10/17
     * 检查集合是否为null或者empty
     */
    public static <T> Boolean checkNullEmpty(Collection<T> collection) {
        if (checkNull(collection))
            return true;
        if (collection.isEmpty())
            return true;
        else
            return false;
    }


    /**
     * @param obj
     * @param defaultValue
     * @param <T>
     * @return 设置的值
     * @author 丁峰
     * 空值默认值设置
     */
    public static <T> T setNullDefault(T obj, T defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        return obj;
    }

    /**
     * @param number
     * @return
     * @author sx-9129 2016/10/20
     * 检查参数是否为负数
     */
    public static Boolean checkMinusOrZero(Integer number) {
        if (number <= 0) {
            return true;
        }
        return false;
    }

    public static Boolean checkMinusOrZero(Long number) {
        if (number <= 0) {
            return true;
        }
        return false;
    }

    public static Boolean checkMinus(Long number) {
        if (number < 0) {
            return true;
        }
        return false;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return pageno不得小于1，pagesize不得小于1
     * @author sx-9129 2016年10月20日
     * 判断传入的页码信息是否是有效的
     */
    public static Boolean checkoutPageInfo(Integer pageNo, Integer pageSize) {
        if (checkNulls(pageNo, pageSize)) {
            return false;
        }
        if (pageNo != null && pageNo.intValue() < 1) {
            return false;
        }
        if (pageSize != null && pageSize.intValue() < 1) {
            return false;
        }
        return true;
    }

    /**
     * @param offSet
     * @param size
     * @return offset不得小于0，size不得小于1
     * @author sx-9129 2016年10月20日
     * 判断传入的偏移信息是否是有效的
     */
    public static Boolean checkoutOffSetInfo(Integer offSet, Integer size) {
        if (checkNulls(offSet, size)) {
            return false;
        }
        if (offSet != null && offSet.intValue() < 0) {
            return false;
        }
        if (size != null && size.intValue() < 1) {
            return false;
        }
        return true;
    }

    /**
     * @param dateFrom
     * @param dateTo
     * @return
     * @author sx-9129 2016年12月07日
     * 检查时间区间是否符合规则。dateFrom <= dateTo < now（当天不可查）以天为单位
     */
    public static Boolean checkDateInterval(Date dateFrom, Date dateTo) {
        if (checkNulls(dateFrom, dateTo))
            return false;
        String from = DateUtil.getYearMonthDay(dateFrom);
        String to = DateUtil.getYearMonthDay(dateTo);
        String now = DateUtil.getYearMonthDay(DateUtil.getToday());
        if (from.compareTo(to) <= 0 && to.compareTo(now) < 0) {
            return true;
        }
        return false;
    }

    public static boolean checkNotNullEmpty(String obj) {
        return obj != null && !obj.isEmpty();
    }
    public static boolean checkNullEmpty(String obj) {
        return obj == null || obj.isEmpty();
    }
    public static boolean checkIP(String obj) {
        if (checkNotNullEmpty(obj)) {
            // 定义正则表达式
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            return obj.matches(regex);
        }
        return false;
    }

    public static boolean checkNumber(String obj) {
        if (checkNotNullEmpty(obj)) {
            String regex = "[0-9]*";
            return obj.matches(regex);
        }
        return false;
    }

    public static boolean checkPort(String obj) {
        if (checkNotNullEmpty(obj)) {
            try {
                int i = Integer.parseInt(obj);
                return i > 0 && i < 65536;
            } catch (NumberFormatException exception) {
                return false;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        String ip = "1";
        System.out.println(checkIP(ip));
    }
}
