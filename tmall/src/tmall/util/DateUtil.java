package tmall.util;

/**
 * @author: bytedance
 * @Email: shenhao.leon@bytedance.com
 * @date: 2022-07-06 8:48 下午
 * @desc:
 */
public class DateUtil {
    public static java.sql.Timestamp d2t(java.util.Date d) {
        if (null == d) return null;
        return new java.sql.Timestamp(d.getTime());
    }

    public static java.util.Date t2d(java.sql.Timestamp t) {
        if (null == t) return null;
        return new java.util.Date(t.getTime());
    }
}
