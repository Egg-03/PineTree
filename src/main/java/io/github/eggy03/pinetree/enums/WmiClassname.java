package io.github.eggy03.pinetree.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Contains names of some classes which are a part of the {@code root/cimv2} namespace of WMI.
 */
@RequiredArgsConstructor
@Getter
public enum WmiClassname {

    /**
     * Retrieves processor information name, core-count, thread-count and more.
     */
    WIN32_PROCESSOR("Win32_Processor"),

    /**
     * Provides cache memory details for L1, L2, and L3 cache levels, for a given Processor.
     */
    WIN32_CACHE_MEMORY("Win32_CacheMemory"),

    /**
     * Describes associations between processors and cache memory instances.
     */
    WIN32_ASSOCIATED_PROCESSOR_MEMORY("Win32_AssociatedProcessorMemory"),

    /**
     * WMI class for Physical Memory (RAM).
     */
    WIN32_PHYSICAL_MEMORY("Win32_PhysicalMemory"),

    /**
     * WMI class for GPU
     */
    WIN32_VIDEO_CONTROLLER("Win32_VideoController"),

    /**
     * WMI class for Monitor
     */
    WIN32_DESKTOP_MONITOR("Win32_DesktopMonitor");

    private final String className;
}