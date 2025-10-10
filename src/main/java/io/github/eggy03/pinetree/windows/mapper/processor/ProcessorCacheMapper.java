package io.github.eggy03.pinetree.windows.mapper.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.processor.ProcessorCache;
import io.github.eggy03.pinetree.windows.enums.processor.ProcessorCacheProperty;
import io.github.eggy03.pinetree.windows.mapper.CommonMappingInterface;

import java.util.ArrayList;
import java.util.List;

import static io.github.eggy03.pinetree.windows.util.CastUtil.toIntegerValue;
import static io.github.eggy03.pinetree.windows.util.CastUtil.toLongValue;
import static io.github.eggy03.pinetree.windows.util.CastUtil.toStringValue;

/**
 * Converts raw WMI query results into {@link ProcessorCache} entities.
 * <p>
 * Methods of this class map the result provided by {@link WbemcliUtil.WmiResult} to
 * immutable {@link ProcessorCache} objects for easier consumption within the application.
 *
 * <h2>Thread Safety</h2>
 * Instances are stateless and therefore thread-safe.
 * @since 1.0
 */
public class ProcessorCacheMapper implements CommonMappingInterface<ProcessorCache, ProcessorCacheProperty> {

    /**
     * Maps WMI result data into a list of {@link ProcessorCache} entities.
     *
     * @param result the WMI query result containing properties defined in {@link ProcessorCacheProperty}
     * @return a list of mapped {@link ProcessorCache} instances
     */
    @Override
    public List<ProcessorCache> toEntityList (WbemcliUtil.WmiResult<ProcessorCacheProperty> result) {

        List<ProcessorCache> processorCacheList = new ArrayList<>();

        for(int i=0; i< result.getResultCount(); i++) {

            ProcessorCache processorCache = ProcessorCache.builder()
                    .deviceId(toStringValue(result.getValue(ProcessorCacheProperty.DeviceID, i)))
                    .purpose(toStringValue(result.getValue(ProcessorCacheProperty.Purpose, i)))
                    .installedSize(toLongValue(result.getValue(ProcessorCacheProperty.InstalledSize, i)))
                    .associativity(toIntegerValue(result.getValue(ProcessorCacheProperty.Associativity, i)))
                    .build();

            processorCacheList.add(processorCache);
        }
        return processorCacheList;
    }
}
