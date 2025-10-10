package io.github.eggy03.pinetree.windows.enums.display;

/**
 * Defines some of the retrievable properties of the {@code Win32_VideoController} WMI class.
 * <p>
 * Each constant represents a property name that can be used to extract
 * specific Video-Controller-related information via a WMI query.
 * @since 1.0
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
