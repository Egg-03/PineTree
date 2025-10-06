package org.pinetree.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Namespace {

    DEFAULT("root/cimv2");

    private final String value;
}
