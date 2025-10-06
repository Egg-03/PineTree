package org.pinetree.util;

import com.sun.jna.platform.win32.COM.WbemcliUtil;

/**
 * Utility class for executing WMI queries and returning typed results.
 * <p>
 * Provides a simple wrapper over {@link WbemcliUtil.WmiQuery}
 * and returns results in the form of {@link WbemcliUtil.WmiResult}
 * </p>
 */
public class WmiUtil {

    private WmiUtil() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Executes a WMI query for the given class and property enumeration.
     *
     * @param nameSpace     the WMI namespace to query, e.g. {@code root/cimv2}
     * @param wmiClassName  the WMI class to query, e.g. {@code Win32_Processor}
     * @param propertyEnum  the enum defining the class properties to retrieve
     * @param <S>           the type of the property enum
     * @return the {@link WbemcliUtil.WmiResult} containing property values
     */
    public static <S extends Enum<S>> WbemcliUtil.WmiResult<S> getResult(String nameSpace, String wmiClassName, Class<S> propertyEnum) {

        WbemcliUtil.WmiQuery<S> query = new WbemcliUtil.WmiQuery<>(nameSpace, wmiClassName, propertyEnum);

        return query.execute();

    }
}
