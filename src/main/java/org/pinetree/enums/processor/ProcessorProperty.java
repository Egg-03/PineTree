package org.pinetree.enums.processor;

/**
 * Defines all retrievable properties of the {@code Win32_Processor} WMI class.
 * <p>
 * Each constant represents a property name that can be used to extract
 * specific processor-related information via a WMI query.
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