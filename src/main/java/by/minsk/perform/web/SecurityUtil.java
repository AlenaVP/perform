package by.minsk.perform.web;

import by.minsk.perform.model.AbstractBaseEntity;

/**
 * @author Alena_Papruha
 * @version 1.1
 * @since 28 Oct, 2020
 */

public class SecurityUtil {

    private static long id = AbstractBaseEntity.START_SEQ;
    private static long performerId = AbstractBaseEntity.START_SEQ + 2;

    private SecurityUtil() {
    }

    public static long authUserId() {
        return id;
    }

    public static void setAuthUserId(long id) {
        SecurityUtil.id = id;
    }

    public static long authPerformerId() {
        return performerId;
    }

    public static void setAuthPerformerId(long id) {
        SecurityUtil.performerId = id;
    }
}
