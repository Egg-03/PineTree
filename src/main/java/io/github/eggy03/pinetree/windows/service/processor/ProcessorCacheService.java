package io.github.eggy03.pinetree.windows.service.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.processor.ProcessorCache;
import io.github.eggy03.pinetree.windows.enums.Namespace;
import io.github.eggy03.pinetree.windows.enums.WmiClassname;
import io.github.eggy03.pinetree.windows.enums.processor.ProcessorCacheProperty;
import io.github.eggy03.pinetree.windows.mapper.processor.ProcessorCacheMapper;
import io.github.eggy03.pinetree.windows.service.CommonServiceInterface;
import io.github.eggy03.pinetree.windows.util.ComUtil;
import io.github.eggy03.pinetree.windows.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving processor-cache-related data from WMI.
 * <p>
 * Contacts {@link WmiUtil} to fetch processor info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link ProcessorCacheMapper} to map the result
 * into a list of {@link ProcessorCache} entities.
 * </p>
 * <h2>Thread Safety</h2>
 * Instances are stateless and thread-safe.
 * @since 1.0
 */
public class ProcessorCacheService implements CommonServiceInterface<ProcessorCache> {

    /**
     * Retrieves a list of processor caches from the system.
     * <p>
     * This method requires you to manually initialize and uninitialize the COM library.
     * </p>
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<ProcessorCache> cacheList = new ProcessorCacheService.get();
     *     cacheList.forEach(cache -> System.out.println(cache.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }
     * </pre>
     * @return list of {@link ProcessorCache} instances retrieved via WMI
     */
    @Override
    public List<ProcessorCache> get() {

        WbemcliUtil.WmiResult<ProcessorCacheProperty> result = WmiUtil.getResult(
                Namespace.DEFAULT.getValue(),
                WmiClassname.WIN32_CACHE_MEMORY.getClassName(),
                ProcessorCacheProperty.class
        );

        return new ProcessorCacheMapper().toEntityList(result);
    }

    /**
     * Retrieves a list of processor caches from the system.
     * <p>
     * This method automatically handles COM setup and cleanup,
     * so you don't need to initialize COM manually.
     * </p>
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * List<ProcessorCache> cacheList = new ProcessorCacheService.getManaged();
     * cacheList.forEach(cache -> System.out.println(cache.toString());
     * }
     * </pre>
     * @return list of {@link ProcessorCache} instances retrieved via WMI
     */
    @Override
    public List<ProcessorCache> getManaged() {

        try {
            ComUtil.initialize();

            WbemcliUtil.WmiResult<ProcessorCacheProperty> result = WmiUtil.getResult(
                    Namespace.DEFAULT.getValue(),
                    WmiClassname.WIN32_CACHE_MEMORY.getClassName(),
                    ProcessorCacheProperty.class
            );

            return new ProcessorCacheMapper().toEntityList(result);

        } finally {
            ComUtil.uninitialize();
        }
    }
}
