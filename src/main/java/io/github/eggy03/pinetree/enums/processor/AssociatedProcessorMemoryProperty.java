package io.github.eggy03.pinetree.enums.processor;

import io.github.eggy03.pinetree.entity.processor.Processor;
import io.github.eggy03.pinetree.entity.processor.ProcessorCache;

/**
 * Defines some of the retrievable properties of the {@code Win32_AssociatedProcessorMemory} WMI class.
 * <p>
 * {@link AssociatedProcessorMemoryProperty#Antecedent} represents {@link ProcessorCache#getDeviceId()}
 * and {@link AssociatedProcessorMemoryProperty#Dependent} represents {@link Processor#getDeviceId()}
 * <p>These properties help associate a {@link Processor} with its {@link ProcessorCache}</p>
 */
public enum AssociatedProcessorMemoryProperty {

    Antecedent,
    Dependent
}
