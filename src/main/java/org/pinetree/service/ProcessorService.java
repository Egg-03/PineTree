package org.pinetree.service;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.constants.enums.NamespaceEnum;
import org.pinetree.constants.enums.ProcessorPropertyEnum;
import org.pinetree.constants.enums.WmiClassnameEnum;
import org.pinetree.entity.Processor;
import org.pinetree.mapper.ProcessorMapper;
import org.pinetree.util.ComUtil;
import org.pinetree.util.WmiUtil;

import java.util.List;

public class ProcessorService {

    /**
     * Caller has to manage COM initialization and uninitialization.
     */
    public List<Processor> getProcessors() {

        // Use the WMI Util Class to get the result
        WbemcliUtil.WmiResult<ProcessorPropertyEnum> result = WmiUtil.getResult(
                NamespaceEnum.DEFAULT.getValue(),
                WmiClassnameEnum.WIN32_PROCESSOR.getClassName(),
                ProcessorPropertyEnum.class
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
            WbemcliUtil.WmiResult<ProcessorPropertyEnum> result = WmiUtil.getResult(
                    NamespaceEnum.DEFAULT.getValue(),
                    WmiClassnameEnum.WIN32_PROCESSOR.getClassName(),
                    ProcessorPropertyEnum.class
            );

            // map the result set into a plain old java object
            return new ProcessorMapper().toEntityList(result);

        } finally {
            // Un-initialize COM
            ComUtil.uninitialize();
        }
    }
}
