package org.pinetree.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WmiClassname {

    WIN32_PROCESSOR("Win32_Processor"),
    WIN32_CACHE_MEMORY("Win32_CacheMemory"),
    WIN32_ASSOCIATED_PROCESSOR_MEMORY("Win32_AssociatedProcessorMemory");

    private final String className;
}
