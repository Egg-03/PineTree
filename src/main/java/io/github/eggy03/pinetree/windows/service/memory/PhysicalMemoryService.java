package io.github.eggy03.pinetree.windows.service.memory;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.memory.PhysicalMemory;
import io.github.eggy03.pinetree.windows.enums.Namespace;
import io.github.eggy03.pinetree.windows.enums.WmiClassname;
import io.github.eggy03.pinetree.windows.enums.memory.PhysicalMemoryProperty;
import io.github.eggy03.pinetree.windows.mapper.memory.PhysicalMemoryMapper;
import io.github.eggy03.pinetree.windows.service.CommonServiceInterface;
import io.github.eggy03.pinetree.windows.util.ComUtil;
import io.github.eggy03.pinetree.windows.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving physical-memory-related data from WMI.
 * <p>
 * Contacts {@link WmiUtil} to fetch RAM info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link PhysicalMemoryMapper} to map the result
 * into a list of {@link PhysicalMemory} entities.
 * </p>
 * <h5>Thread Safety</h5>
 * Instances are stateless and thread-safe.
 */
public class PhysicalMemoryService implements CommonServiceInterface<PhysicalMemory> {

    /**
     * Retrieves a list of physical memories (RAM Sticks) from the system.
     * <p>
     * The caller is responsible for initializing and uninitializing COM.
     *
     * <h5>Usage Example</h5>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<PhysicalMemory> memoryList = new PhysicalMemoryService().get();
     *     memoryList.forEach(memory -> System.out.println(memory.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }
     * </pre>
     * @return list of {@link PhysicalMemory} instances retrieved via WMI
     */
    @Override
    public List<PhysicalMemory> get() {

        WbemcliUtil.WmiResult<PhysicalMemoryProperty> result = WmiUtil.getResult(
                Namespace.DEFAULT.getValue(),
                WmiClassname.WIN32_PHYSICAL_MEMORY.getClassName(),
                PhysicalMemoryProperty.class
        );

        return new PhysicalMemoryMapper().toEntityList(result);
    }

    /**
     * Retrieves physical-memory related data while managing COM initialization and cleanup automatically.
     * <p>
     * Recommended for most callers who do not require manual COM control.
     *
     * <h5>Usage Example</h5>
     * <pre>{@code
     * List<PhysicalMemory> memoryList = new PhysicalMemoryService().getManaged();
     * memoryList.forEach(memory -> System.out.println(memory.toString());
     * }
     * </pre>
     * @return list of {@link PhysicalMemory} instances retrieved via WMI
     */
    @Override
    public List<PhysicalMemory> getManaged() {

        try{
            ComUtil.initialize();

            WbemcliUtil.WmiResult<PhysicalMemoryProperty> result = WmiUtil.getResult(
                    Namespace.DEFAULT.getValue(),
                    WmiClassname.WIN32_PHYSICAL_MEMORY.getClassName(),
                    PhysicalMemoryProperty.class
            );

            return new PhysicalMemoryMapper().toEntityList(result);

        } finally {
            ComUtil.uninitialize();
        }
    }
}
