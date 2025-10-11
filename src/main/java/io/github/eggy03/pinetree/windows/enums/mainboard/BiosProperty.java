package io.github.eggy03.pinetree.windows.enums.mainboard;

/**
 * Defines some of the retrievable properties of the {@code Win32_BIOS} WMI class.
 * <p>
 * Each constant represents a property name that can be used to extract
 * specific BIOS-related information via a WMI query.
 * @since 1.0
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-bios">Win32_BIOS</a>
 */
public enum BiosProperty {
    Name,
    Caption,
    Manufacturer,
    ReleaseDate,
    SMBIOSPresent,
    Status,
    Version,
    CurrentLanguage,
    SMBIOSBIOSVersion,
    PrimaryBIOS
}
