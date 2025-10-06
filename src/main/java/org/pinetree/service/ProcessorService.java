package org.pinetree.service;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.constants.enums.NamespaceEnum;
import org.pinetree.constants.enums.ProcessorPropertyEnum;
import org.pinetree.constants.enums.WmiClassnameEnum;
import org.pinetree.entity.Processor;
import org.pinetree.util.ComUtil;
import org.pinetree.util.WmiUtil;

import java.util.ArrayList;
import java.util.List;

public class ProcessorService {

    /**
     * Caller has to manage COM initialization and uninitialization.
     */
    public List<Processor> getProcessors() {

        List<Processor> processorList = new ArrayList<>();

        // Use the Util Class to get the result
        WbemcliUtil.WmiResult<ProcessorPropertyEnum> result = WmiUtil.getResult(
                NamespaceEnum.DEFAULT.getValue(),
                WmiClassnameEnum.WIN32_PROCESSOR.getClassName(),
                ProcessorPropertyEnum.class
        );

        // Enumerate through the results
        for(int i=0; i< result.getResultCount(); i++) {

            // Build Processor Object
            Processor processor = Processor.builder()
                    .deviceId((String) result.getValue(ProcessorPropertyEnum.DeviceID, i))
                    .name((String) result.getValue(ProcessorPropertyEnum.Name, i))
                    .numberOfCores((Integer) result.getValue(ProcessorPropertyEnum.NumberOfCores, i))
                    .threadCount((Integer) result.getValue(ProcessorPropertyEnum.ThreadCount, i))
                    .numberOfLogicalProcessors((Integer) result.getValue(ProcessorPropertyEnum.NumberOfLogicalProcessors, i))
                    .manufacturer((String) result.getValue(ProcessorPropertyEnum.Manufacturer, i))
                    .addressWidth((Integer) result.getValue(ProcessorPropertyEnum.AddressWidth, i))
                    .l2CacheSize((Integer) result.getValue(ProcessorPropertyEnum.L2CacheSize, i))
                    .l3CacheSize((Integer) result.getValue(ProcessorPropertyEnum.L3CacheSize, i))
                    .maxClockSpeed((Integer) result.getValue(ProcessorPropertyEnum.MaxClockSpeed, i))
                    .extClock((Integer) result.getValue(ProcessorPropertyEnum.ExtClock, i))
                    .socketDesignation((String) result.getValue(ProcessorPropertyEnum.SocketDesignation, i))
                    .version((String) result.getValue(ProcessorPropertyEnum.Version, i))
                    .caption((String) result.getValue(ProcessorPropertyEnum.Caption, i))
                    .family((Integer) result.getValue(ProcessorPropertyEnum.Family, i))
                    .stepping((String) result.getValue(ProcessorPropertyEnum.Stepping, i))
                    .virtualizationFirmwareEnabled((Boolean) result.getValue(ProcessorPropertyEnum.VirtualizationFirmwareEnabled, i))
                    .processorId((String) result.getValue(ProcessorPropertyEnum.ProcessorId, i))
                    .build();

            processorList.add(processor);
        }
        return processorList;
    }

    /**
     * Caller does not have to manage COM initialization and uninitialization.
     */
    public List<Processor> getProcessorsManaged() {

        try {
            // Initialize COM
            ComUtil.initialize();

            return getProcessors();

        } finally {
            ComUtil.uninitialize();
        }
    }
}
