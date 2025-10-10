package io.github.eggy03.pinetree.windows.enums.processor;

/**
 * Defines some of the retrievable properties of the {@code Win32_CacheMemory} WMI class.
 * <p>
 * Each constant represents a property name that can be used to extract
 * specific processor-cache-related information via a WMI query.
 * </p>
 * Values are case-sensitive an exactly represent the properties defined in the official Microsoft documentation
 * for {@code Win32_CacheMemory} class
 * @since 1.0
 *@see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-cachememory">Win32_CacheMemory</a>
 */
public enum ProcessorCacheProperty {

    DeviceID,
    Purpose,
    InstalledSize,
    Associativity

}
