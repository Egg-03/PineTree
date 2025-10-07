package org.pinetree.service.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.entity.processor.AssociatedProcessorMemory;
import org.pinetree.entity.processor.Processor;
import org.pinetree.entity.processor.ProcessorCache;
import org.pinetree.enums.Namespace;
import org.pinetree.enums.WmiClassname;
import org.pinetree.enums.processor.AssociatedProcessorMemoryProperty;
import org.pinetree.mapper.processor.AssociatedProcessorMemoryMapper;
import org.pinetree.util.ComUtil;
import org.pinetree.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving {@link AssociatedProcessorMemory} which links {@link Processor}
 * with {@link ProcessorCache}.
 * <p>
 * Contacts {@link WmiUtil} to fetch processor info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link AssociatedProcessorMemoryMapper} to map the result
 * into a list of {@link AssociatedProcessorMemory} entities.
 * </p>
 * <h5>Thread Safety</h5>
 * Instances are stateless and thread-safe.
 */
public class AssociatedProcessorMemoryService {

    /**
     * Retrieves a list of associated processor memory mappings from the system.
     * <p>
     * The caller is responsible for initializing and uninitializing COM.
     *
     * <h5>Usage Example</h5>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<AssociatedProcessorMemory> apmList = new AssociatedProcessorMemoryService().getAssociatedProcessorMemory();
     *     apmList.forEach(apm -> System.out.println(apm.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }
     * </pre>
     * @return list of {@link AssociatedProcessorMemory} instances retrieved via WMI
     */
    public List<AssociatedProcessorMemory> getAssociatedProcessorMemory() {

        WbemcliUtil.WmiResult<AssociatedProcessorMemoryProperty> result = WmiUtil.getResult(
                Namespace.DEFAULT.getValue(),
                WmiClassname.WIN32_ASSOCIATED_PROCESSOR_MEMORY.getClassName(),
                AssociatedProcessorMemoryProperty.class
        );

        return new AssociatedProcessorMemoryMapper().toEntityList(result);
    }

    /**
     * Retrieves associated processor memory mapping related data while managing COM initialization and cleanup automatically.
     * <p>
     * Recommended for most callers who do not require manual COM control.
     *
     * <h5>Usage Example</h5>
     * <pre>{@code
     * List<AssociatedProcessorMemory> apmList = new AssociatedProcessorMemoryService().getAssociatedProcessorMemoryManaged();
     *
     * apmList.forEach(apm -> System.out.println(apm.toString());
     * }
     * </pre>
     * @return list of {@link AssociatedProcessorMemory} instances retrieved via WMI
     */
    public List<AssociatedProcessorMemory> getAssociatedProcessorMemoryManaged() {

        try {
            ComUtil.initialize();

            WbemcliUtil.WmiResult<AssociatedProcessorMemoryProperty> result = WmiUtil.getResult(
                    Namespace.DEFAULT.getValue(),
                    WmiClassname.WIN32_ASSOCIATED_PROCESSOR_MEMORY.getClassName(),
                    AssociatedProcessorMemoryProperty.class
            );

            return new AssociatedProcessorMemoryMapper().toEntityList(result);

        } finally {
            ComUtil.uninitialize();
        }
    }
}
