package org.pinetree.constants.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NamespaceEnum {

    DEFAULT("root/cimv2");

    private final String value;
}
