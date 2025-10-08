package io.github.eggy03.pinetree.enums.display;

/**
 * Defines some of the retrievable properties of the {@code Win32_VideoController} WMI class.
 * <p>
 * Each constant represents a property name that can be used to extract
 * specific Video-Controller-related information via a WMI query.
 */
public enum VideoControllerProperty {

    DeviceID,
    Name,
    PNPDeviceID,
    CurrentBitsPerPixel,
    CurrentHorizontalResolution,
    CurrentVerticalResolution,
    CurrentRefreshRate,
    MaxRefreshRate,
    MinRefreshRate,
    AdapterDACType,
    AdapterRAM,
    DriverDate,
    DriverVersion,
    VideoProcessor
}
