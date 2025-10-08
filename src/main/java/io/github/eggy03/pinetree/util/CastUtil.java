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
        return switch (value) {
            case null -> null;
            case Integer i -> Integer.toUnsignedLong(i);
            case Short s -> Short.toUnsignedLong(s);
            case Number num -> num.longValue();
            default -> Long.parseLong(value.toString().trim());
        };
    }

    @Nullable
    public static Integer toIntegerValue(Object value) {
        return switch (value) {
            case null -> null;
            case Short s -> Short.toUnsignedInt(s);
            case Byte b -> Byte.toUnsignedInt(b);
            case Number num -> num.intValue();
            default -> Integer.parseInt(value.toString().trim());
        };
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
