package org.pinetree.mapper;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.entity.ProcessorCache;
import org.pinetree.enums.processor.ProcessorCacheProperty;

import java.util.ArrayList;
import java.util.List;

public class ProcessorCacheMapper {

    public List<ProcessorCache> toEntityList (WbemcliUtil.WmiResult<ProcessorCacheProperty> result) {

        List<ProcessorCache> processorCacheList = new ArrayList<>();

        for(int i=0; i< result.getResultCount(); i++) {

            ProcessorCache processorCache = ProcessorCache.builder()
                    .deviceId((String) result.getValue(ProcessorCacheProperty.DeviceID, i))
                    .purpose((String) result.getValue(ProcessorCacheProperty.Purpose, i))
                    .installedSize((Integer) result.getValue(ProcessorCacheProperty.InstalledSize, i))
                    .associativity((Integer) result.getValue(ProcessorCacheProperty.Associativity, i))
                    .build();

            processorCacheList.add(processorCache);
        }
        return processorCacheList;
    }
}
