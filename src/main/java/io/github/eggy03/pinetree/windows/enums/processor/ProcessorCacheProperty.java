package io.github.eggy03.pinetree.windows.enums.processor;

/**
 * Defines some of the retrievable properties of the {@code Win32_CacheMemory} WMI class.
 * <p>
 * Each constant represents a property name that can be used to extract
 * specific processor-cache-related information via a WMI query.
 * @since 1.0
 */
public enum ProcessorCacheProperty {

    DeviceID,
    Purpose,
    InstalledSize,
    Associativity

}
