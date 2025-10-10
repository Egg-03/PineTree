package io.github.eggy03.pinetree.windows.util;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Utility class for casting {@link Object} values retrieved from
 * {@link WbemcliUtil.WmiResult#getValue(Enum, int)} into common Java types.
 *
 * <p>
 * Since WMI query results are returned as generic {@link Object} instances, these helper methods
 * handle provide ways to cast them into common Java types.
 * This class is only for use within the library. Example usages include the implementations of the mappers in the
 * {@link io.github.eggy03.pinetree.windows.mapper} package.
 * </p>
 *
 * <h2>Supported Conversions</h2>
 * <ul>
 *     <li>{@link #toLongValue(Object)} – Converts numeric values to {@code Long}, handling unsigned conversions.</li>
 *     <li>{@link #toIntegerValue(Object)} – Converts numeric values to {@code Integer}, handling unsigned conversions.</li>
 *     <li>{@link #toStringValue(Object)} – Converts any value to {@code String}, trimming whitespace.</li>
 *     <li>{@link #toBooleanValue(Object)} – Converts to {@code Boolean}, supporting both native and string-based booleans.</li>
 * </ul>
 * @since 1.0
 */
public final class CastUtil {

    private CastUtil() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Converts a raw WMI property value of {@link Object} type, into a {@link Long}.
     * <p>
     * Also handles unsigned conversions for {@link Integer} and {@link Short} types,
     * and falls back to parsing from {@code toString()} for other types.
     * </p>
     *
     * @param value the raw WMI property value to convert
     * @return the converted {@link Long} value, or {@code null} if the input value is {@code null}
     */
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

    /**
     * Converts a raw WMI property value of {@link Object} type, into an {@link Integer}.
     * <p>
     * Also handles unsigned conversions for {@link Short} and {@link Byte} types,
     * and falls back to parsing from {@code toString()} for other input types.
     * </p>
     *
     * @param value the raw WMI property value to convert
     * @return the converted {@link Integer} value, or {@code null} if the input value is {@code null}
     */
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

    /**
     * Converts a raw WMI property value of {@link Object} type into a trimmed {@link String}.
     *
     * @param value the raw WMI property value to convert
     * @return the string representation, or {@code null} if the input value is {@code null}
     */
    @Nullable
    public static String toStringValue(Object value) {
        if (value == null) return null;
        return value.toString().trim();
    }

    /**
     * Converts a raw WMI property value of {@link Object} type into a {@link Boolean}.
     * <p>
     * Can convert native boolean types as well as string-based representations such as
     * {@code "true"}, {@code "false"}.
     * </p>
     *
     * @param value the raw WMI value to convert
     * @return the boolean representation, or {@code null} if the input value is {@code null}
     */
    @Nullable
    public static Boolean toBooleanValue(Object value) {
        if (value == null) return null;
        if (value instanceof Boolean b) return b;
        return Boolean.parseBoolean(value.toString().trim());
    }
}
