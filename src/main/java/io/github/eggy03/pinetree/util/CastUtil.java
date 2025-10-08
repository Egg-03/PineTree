package io.github.eggy03.pinetree.util;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Utility class that casts {@link Object} values retrieved from
 * {@link WbemcliUtil.WmiResult#getValue(Enum, int)} into common types.
 */
public final class CastUtil {

    private CastUtil() {
        throw new IllegalStateException("Utility Class");
    }

    @Nullable
    public static Long toLongValue(Object value) {
        if (value == null) return null;
        if (value instanceof Number num) return num.longValue();
        return Long.parseLong(value.toString().trim());
    }

    @Nullable
    public static Integer toIntegerValue(Object value) {
        if (value == null) return null;
        if (value instanceof Number num) return num.intValue();
        return Integer.parseInt(value.toString().trim());
    }

    @Nullable
    public static String toStringValue(Object value) {
        if (value == null) return null;
        return value.toString().trim();
    }

    @Nullable
    public static Boolean toBooleanValue(Object value) {
        if (value == null) return null;
        if (value instanceof Boolean b) return b;
        return Boolean.parseBoolean(value.toString().trim());
    }
}
