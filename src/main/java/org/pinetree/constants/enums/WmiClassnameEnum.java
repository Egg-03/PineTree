package org.pinetree.constants.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WmiClassnameEnum {

    WIN32_PROCESSOR("Win32_Processor");

    private final String className;
}
