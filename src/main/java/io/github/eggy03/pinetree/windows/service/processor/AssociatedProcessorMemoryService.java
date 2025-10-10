package io.github.eggy03.pinetree.windows.service.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.processor.AssociatedProcessorMemory;
import io.github.eggy03.pinetree.windows.entity.processor.Processor;
import io.github.eggy03.pinetree.windows.entity.processor.ProcessorCache;
import io.github.eggy03.pinetree.windows.enums.Namespace;
import io.github.eggy03.pinetree.windows.enums.WmiClassname;
import io.github.eggy03.pinetree.windows.enums.processor.AssociatedProcessorMemoryProperty;
import io.github.eggy03.pinetree.windows.mapper.processor.AssociatedProcessorMemoryMapper;
import io.github.eggy03.pinetree.windows.service.CommonServiceInterface;
import io.github.eggy03.pinetree.windows.util.ComUtil;
import io.github.eggy03.pinetree.windows.util.WmiUtil;

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
 * @since 1.0
 */
public class AssociatedProcessorMemoryService implements CommonServiceInterface<AssociatedProcessorMemory> {

    /**
     * Retrieves a list of associated processor memory mappings from the system.
     * <p>
     * The caller is responsible for initializing and uninitializing COM.
     *
     * <h5>Usage Example</h5>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<AssociatedProcessorMemory> apmList = new AssociatedProcessorMemoryService().get();
     *     apmList.forEach(apm -> System.out.println(apm.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }
     * </pre>
     * @return list of {@link AssociatedProcessorMemory} instances retrieved via WMI
     */
    @Override
    public List<AssociatedProcessorMemory> get() {

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
     * List<AssociatedProcessorMemory> apmList = new AssociatedProcessorMemoryService().getManaged();
     *
     * apmList.forEach(apm -> System.out.println(apm.toString());
     * }
     * </pre>
     * @return list of {@link AssociatedProcessorMemory} instances retrieved via WMI
     */
    @Override
    public List<AssociatedProcessorMemory> getManaged() {

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
