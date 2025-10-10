package io.github.eggy03.pinetree.windows.enums.memory;

/**
 * Defines some of the retrievable properties of the {@code Win32_PhysicalMemory} WMI class.
 * <p>
 * Each constant represents a property name that can be used to extract
 * specific RAM-related information via a WMI query.
 * @since 1.0
 */
public enum PhysicalMemoryProperty {

    Tag,
    Name,
    Manufacturer,
    Model,
    OtherIdentifyingInfo,
    PartNumber,
    FormFactor,
    BankLabel,
    Capacity,
    DataWidth,
    Speed,
    ConfiguredClockSpeed,
    DeviceLocator,
    SerialNumber
}
