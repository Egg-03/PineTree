package org.pinetree.service.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.entity.processor.AssociatedProcessorMemory;
import org.pinetree.enums.Namespace;
import org.pinetree.enums.WmiClassname;
import org.pinetree.enums.processor.AssociatedProcessorMemoryProperty;
import org.pinetree.mapper.processor.AssociatedProcessorMemoryMapper;
import org.pinetree.util.ComUtil;
import org.pinetree.util.WmiUtil;

import java.util.List;

public class AssociatedProcessorMemoryService {

    public List<AssociatedProcessorMemory> getAssociatedProcessorMemory() {

        WbemcliUtil.WmiResult<AssociatedProcessorMemoryProperty> result = WmiUtil.getResult(
                Namespace.DEFAULT.getValue(),
                WmiClassname.WIN32_ASSOCIATED_PROCESSOR_MEMORY.getClassName(),
                AssociatedProcessorMemoryProperty.class
        );

        return new AssociatedProcessorMemoryMapper().toEntityList(result);
    }

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
