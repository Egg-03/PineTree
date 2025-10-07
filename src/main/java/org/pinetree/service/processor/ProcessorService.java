package org.pinetree.service.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.entity.processor.Processor;
import org.pinetree.enums.Namespace;
import org.pinetree.enums.WmiClassname;
import org.pinetree.enums.processor.ProcessorProperty;
import org.pinetree.mapper.processor.ProcessorMapper;
import org.pinetree.util.ComUtil;
import org.pinetree.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving processor-related data from WMI.
 * <p>
 * Contacts {@link WmiUtil} to fetch processor info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link ProcessorMapper} to map the result
 * into a list of {@link Processor} entities.
 * </p>
 * <h5>Thread Safety</h5>
 * Instances are stateless and thread-safe.
 */
public class ProcessorService {

    /**
     * Retrieves a list of processors from the system.
     * <p>
     * The caller is responsible for initializing and uninitializing COM.
     *
     * <h5>Usage Example</h5>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<Processor> cpuList = new ProcessorService.getProcessors();
     *     cpuList.forEach(cpu -> System.out.println(cpu.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }
     * </pre
     * @return list of {@link Processor} instances retrieved via WMI
     */
    public List<Processor> getProcessors() {

        // Use the WMI Util Class to get the result
        WbemcliUtil.WmiResult<ProcessorProperty> result = WmiUtil.getResult(
                Namespace.DEFAULT.getValue(),
                WmiClassname.WIN32_PROCESSOR.getClassName(),
                ProcessorProperty.class
        );

        // map the result set into a plain old java object
        return new ProcessorMapper().toEntityList(result);
    }

    /**
     * Retrieves processor data while managing COM initialization and cleanup automatically.
     * <p>
     * Recommended for most callers who do not require manual COM control.
     *
     * <h5>Usage Example</h5>
     * <pre>{@code
     * List<Processor> cpuList = new ProcessorCacheService.getProcessorsManaged();
     * cpuList.forEach(cpu -> System.out.println(cpu.toString());
     * }
     * </pre>
     * @return list of {@link Processor} instances retrieved via WMI
     */
    public List<Processor> getProcessorsManaged() {

        try {
            // Initialize COM
            ComUtil.initialize();

            // Use the WMI Util Class to get the result
            WbemcliUtil.WmiResult<ProcessorProperty> result = WmiUtil.getResult(
                    Namespace.DEFAULT.getValue(),
                    WmiClassname.WIN32_PROCESSOR.getClassName(),
                    ProcessorProperty.class
            );

            // map the result set into a plain old java object
            return new ProcessorMapper().toEntityList(result);

        } finally {
            // Un-initialize COM
            ComUtil.uninitialize();
        }
    }
}
