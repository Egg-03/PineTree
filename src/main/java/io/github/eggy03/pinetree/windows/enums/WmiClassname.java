package io.github.eggy03.pinetree.windows.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Contains names of some classes which are a part of the {@code root/cimv2} namespace of WMI.
 */
@RequiredArgsConstructor
@Getter
public enum WmiClassname {

    /**
     * WMI class name for {@code Win32_Processor}
     */
    WIN32_PROCESSOR("Win32_Processor"),

    /**
     * WMI class name for {@code Win32_CacheMemory}
     */
    WIN32_CACHE_MEMORY("Win32_CacheMemory"),

    /**
     * WMI class name for {@code Win32_AssociatedProcessorMemory}
     */
    WIN32_ASSOCIATED_PROCESSOR_MEMORY("Win32_AssociatedProcessorMemory"),

    /**
     * WMI class name for {@code Win32_PhysicalMemory}
     */
    WIN32_PHYSICAL_MEMORY("Win32_PhysicalMemory"),

    /**
     * WMI class name for {@code Win32_VideoController}
     */
    WIN32_VIDEO_CONTROLLER("Win32_VideoController"),

    /**
     * WMI class name for {@code Win32_DesktopMonitor}
     */
    WIN32_DESKTOP_MONITOR("Win32_DesktopMonitor");

    private final String className;
}