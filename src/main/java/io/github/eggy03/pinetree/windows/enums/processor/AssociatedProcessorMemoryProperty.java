package io.github.eggy03.pinetree.windows.enums.processor;

import io.github.eggy03.pinetree.windows.entity.processor.Processor;
import io.github.eggy03.pinetree.windows.entity.processor.ProcessorCache;

/**
 * Defines some of the retrievable properties of the {@code Win32_AssociatedProcessorMemory} WMI class.
 * <p>
 * {@link AssociatedProcessorMemoryProperty#Antecedent} represents {@link ProcessorCache}
 * and {@link AssociatedProcessorMemoryProperty#Dependent} represents {@link Processor}
 *
 * <p>These properties help associate a {@link Processor} with its {@link ProcessorCache}</p>
 *
 * Values are case-sensitive an exactly represent the properties defined in the official Microsoft documentation
 * for {@code Win32_AssociatedProcessorMemory} class
 *
 * @since 1.0
 * @see <a href="https://powershell.one/wmi/root/cimv2/win32_associatedprocessormemory">Win32_AssociatedProcessorMemory</a>
 */
public enum AssociatedProcessorMemoryProperty {

    Antecedent,
    Dependent
}
