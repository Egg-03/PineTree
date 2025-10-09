package io.github.eggy03.pinetree.enums.display;

/**
 * Defines some of the retrievable properties of the {@code Win32_DesktopMonitor} WMI class.
 * <p>
 * Each constant represents a property name that can be used to extract
 * specific Monitor-related information via a WMI query.
 */
public enum MonitorProperty {

    DeviceID,
    Name,
    PNPDeviceID,
    Status,
    MonitorManufacturer,
    MonitorType,
    PixelsPerXLogicalInch,
    PixelsPerYLogicalInch
}
