package io.github.eggy03.pinetree.windows.enums.processor;

import io.github.eggy03.pinetree.windows.entity.processor.Processor;
import io.github.eggy03.pinetree.windows.entity.processor.ProcessorCache;

/**
 * Defines some of the retrievable properties of the {@code Win32_AssociatedProcessorMemory} WMI class.
 * <p>
 * {@link AssociatedProcessorMemoryProperty#Antecedent} represents {@link ProcessorCache#getDeviceId()}
 * and {@link AssociatedProcessorMemoryProperty#Dependent} represents {@link Processor#getDeviceId()}
 * <p>These properties help associate a {@link Processor} with its {@link ProcessorCache}</p>
 * @since 1.0
 */
public enum AssociatedProcessorMemoryProperty {

    Antecedent,
    Dependent
}
