package io.github.eggy03.pinetree.windows.enums.mainboard;

/**
 * Defines some of the retrievable properties of the {@code Win32_Baseboard} WMI class.
 * <p>
 * Each constant represents a property name that can be used to extract
 * specific Motherboard-related information via a WMI query.
 * @since 1.0
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-baseboard">Win32_Baseboard</a>
 */
public enum BaseboardProperty {
    Manufacturer,
    Model,
    Product,
    SerialNumber,
    Version
}
