package com.bsoft.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mabaoying
 * @ClassName: DBObjHelper
 * @Description: TODO
 * @date: 2019/11/21 16:52
 * @最后修改人:
 * @最后修改时间:
 */

public class DBObjHelper {

    public static float dbObjToFloat(Object object) {
        String value = dbObjToString(object);
        if (value != null && value != "")
            return Float.valueOf(value);
        return 0;
    }

    public static int dbObjToInt(Object object) {
        String value = dbObjToString(object);
        if (value != null && value != "")
            return Integer.valueOf(value);
        return 0;
    }

    public static String dbObjToString(Object object) {
        if (object != null)
            return object.toString();
        return "";
    }

    public static Date dbObjToDate(Object object) {
        if (object != null)
            return (Date) object;
        return null;
    }

    public static boolean dbObjToBool(Object object) {
        return dbObjToString(object).equals("1");
    }

    public static BigDecimal dbObjToDecimal(Object object) {
        return BigDecimal.valueOf(dbObjToInt(object));
    }
}
