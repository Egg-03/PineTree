package org.pinetree.util;

import com.sun.jna.platform.win32.COM.WbemcliUtil;

public class WmiUtil {

    private WmiUtil() {
        throw new IllegalStateException("Utility Class");
    }

    public static <S extends Enum<S>> WbemcliUtil.WmiResult<S> getResult(String nameSpace, String wmiClassName, Class<S> propertyEnum) {

        WbemcliUtil.WmiQuery<S> query = new WbemcliUtil.WmiQuery<>(nameSpace, wmiClassName, propertyEnum);

        return query.execute();

    }
}
