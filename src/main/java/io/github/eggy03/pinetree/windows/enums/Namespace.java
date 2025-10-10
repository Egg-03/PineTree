package io.github.eggy03.pinetree.windows.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents Windows Management Instrumentation (WMI) namespaces used for system queries.
 * <p>
 * Each constant defines a root namespace from which WMI classes can be accessed.
 * Currently, only the default namespace {@code root/cimv2} is supported.
 */
@RequiredArgsConstructor
@Getter
public enum Namespace {

    /**
     * The default WMI namespace containing standard CIM and Win32 classes.
     */
    DEFAULT("root/cimv2");

    private final String value;
}