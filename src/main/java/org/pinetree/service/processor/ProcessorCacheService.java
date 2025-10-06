package org.pinetree.service.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.entity.processor.ProcessorCache;
import org.pinetree.enums.Namespace;
import org.pinetree.enums.WmiClassname;
import org.pinetree.enums.processor.ProcessorCacheProperty;
import org.pinetree.mapper.processor.ProcessorCacheMapper;
import org.pinetree.util.ComUtil;
import org.pinetree.util.WmiUtil;

import java.util.List;

public class ProcessorCacheService {

    public List<ProcessorCache> getProcessorCaches() {

        WbemcliUtil.WmiResult<ProcessorCacheProperty> result = WmiUtil.getResult(
                Namespace.DEFAULT.getValue(),
                WmiClassname.WIN32_CACHE_MEMORY.getClassName(),
                ProcessorCacheProperty.class
        );

        return new ProcessorCacheMapper().toEntityList(result);
    }

    public List<ProcessorCache> getProcessorCachesManaged() {

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
