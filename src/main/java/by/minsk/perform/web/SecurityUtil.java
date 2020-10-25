package by.minsk.perform.web;

import by.minsk.perform.model.AbstractBaseEntity;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 26 Oct, 2020
 */

public class SecurityUtil {

    private static long id = AbstractBaseEntity.START_SEQ;

    private SecurityUtil() {
    }

    public static long authUserId() {
        return id;
    }

    public static void setAuthUserId(long id) {
        SecurityUtil.id = id;
    }
}
