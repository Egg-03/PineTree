package io.github.eggy03.pinetree.windows.service.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.processor.Processor;
import io.github.eggy03.pinetree.windows.enums.Namespace;
import io.github.eggy03.pinetree.windows.enums.WmiClassname;
import io.github.eggy03.pinetree.windows.enums.processor.ProcessorProperty;
import io.github.eggy03.pinetree.windows.mapper.processor.ProcessorMapper;
import io.github.eggy03.pinetree.windows.service.CommonServiceInterface;
import io.github.eggy03.pinetree.windows.util.ComUtil;
import io.github.eggy03.pinetree.windows.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving processor-related data from WMI.
 * <p>
 * Contacts {@link WmiUtil} to fetch processor info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link ProcessorMapper} to map the result
 * into a list of {@link Processor} entities.
 * </p>
 * <h2>Thread Safety</h2>
 * Instances are stateless and thread-safe.
 * @since 1.0
 */
public class ProcessorService implements CommonServiceInterface<Processor> {

    /**
     * Retrieves a list of processors from the system.
     * <p>
     * The caller is responsible for initializing and uninitializing COM.
     * </p>
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<Processor> cpuList = new ProcessorService.get();
     *     cpuList.forEach(cpu -> System.out.println(cpu.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }</pre>
     * @return list of {@link Processor} instances retrieved via WMI
     */
    @Override
    public List<Processor> get() {

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
     * </p>
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * List<Processor> cpuList = new ProcessorCacheService.getManaged();
     * cpuList.forEach(cpu -> System.out.println(cpu.toString());
     * }</pre>
     *
     * @return list of {@link Processor} instances retrieved via WMI
     */
    @Override
    public List<Processor> getManaged() {

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
