package io.github.eggy03.pinetree.windows.enums.processor;

/**
 * Defines some of the retrievable properties of the {@code Win32_Processor} WMI class.
 * <p>
 * Each constant represents a property name that can be used to extract
 * specific processor-related information via a WMI query.
 * </p>
 * Values are case-sensitive an exactly represent the properties defined in the official Microsoft documentation
 * for {@code Win32_Processor} class
 * @since 1.0
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-processor">Win32_Processor</a>
 */
public enum ProcessorProperty {

    DeviceID,
    Name,
    NumberOfCores,
    ThreadCount,
    NumberOfLogicalProcessors,
    Manufacturer,
    AddressWidth,
    L2CacheSize,
    L3CacheSize,
    MaxClockSpeed,
    ExtClock,
    SocketDesignation,
    Version,
    Caption,
    Family,
    Stepping,
    VirtualizationFirmwareEnabled,
    ProcessorId
}