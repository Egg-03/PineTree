package io.github.eggy03.pinetree.service.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.entity.processor.ProcessorCache;
import io.github.eggy03.pinetree.enums.Namespace;
import io.github.eggy03.pinetree.enums.WmiClassname;
import io.github.eggy03.pinetree.enums.processor.ProcessorCacheProperty;
import io.github.eggy03.pinetree.mapper.processor.ProcessorCacheMapper;
import io.github.eggy03.pinetree.service.CommonServiceInterface;
import io.github.eggy03.pinetree.util.ComUtil;
import io.github.eggy03.pinetree.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving processor-cache-related data from WMI.
 * <p>
 * Contacts {@link WmiUtil} to fetch processor info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link ProcessorCacheMapper} to map the result
 * into a list of {@link ProcessorCache} entities.
 * </p>
 * <h5>Thread Safety</h5>
 * Instances are stateless and thread-safe.
 */
public class ProcessorCacheService implements CommonServiceInterface<ProcessorCache> {

    /**
     * Retrieves a list of processor caches from the system.
     * <p>
     * The caller is responsible for initializing and uninitializing COM.
     *
     * <h5>Usage Example</h5>
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
     * Retrieves processor-cache related data while managing COM initialization and cleanup automatically.
     * <p>
     * Recommended for most callers who do not require manual COM control.
     *
     * <h5>Usage Example</h5>
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
