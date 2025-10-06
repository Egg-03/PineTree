package org.pinetree.service.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.enums.Namespace;
import org.pinetree.enums.processor.ProcessorProperty;
import org.pinetree.enums.WmiClassname;
import org.pinetree.entity.processor.Processor;
import org.pinetree.mapper.processor.ProcessorMapper;
import org.pinetree.util.ComUtil;
import org.pinetree.util.WmiUtil;

import java.util.List;

public class ProcessorService {

    /**
     * Caller has to manage COM initialization and uninitialization.
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
     * Caller does not have to manage COM initialization and uninitialization.
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
