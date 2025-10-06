package org.pinetree.service;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.pinetree.constants.enums.ProcessorEnum;
import org.pinetree.entity.Processor;
import org.pinetree.util.ComUtil;

import java.util.ArrayList;
import java.util.List;

public class ProcessorService {

    public List<Processor> getProcessor() {

        List<Processor> processorList= new ArrayList<>();
        try {
            // Initialize COM
            ComUtil.initialize();

            // Instantiate Query
            WbemcliUtil.WmiQuery<ProcessorEnum> query = new WbemcliUtil.WmiQuery<>(
                    "root/cimv2",
                    "Win32_Processor",
                    ProcessorEnum.class
            );

            // Get Result
            WbemcliUtil.WmiResult<ProcessorEnum> result = query.execute();

            // Enumerate through the results
            for(int i=0; i< result.getResultCount(); i++) {

                Processor processor = Processor.builder()
                        .deviceId((String) result.getValue(ProcessorEnum.DeviceID, i))
                        .name((String) result.getValue(ProcessorEnum.Name, i))
                        .numberOfCores((Integer) result.getValue(ProcessorEnum.NumberOfCores, i))
                        .threadCount((Integer) result.getValue(ProcessorEnum.ThreadCount, i))
                        .numberOfLogicalProcessors((Integer) result.getValue(ProcessorEnum.NumberOfLogicalProcessors, i))
                        .manufacturer((String) result.getValue(ProcessorEnum.Manufacturer, i))
                        .addressWidth((Integer) result.getValue(ProcessorEnum.AddressWidth, i))
                        .l2CacheSize((Integer) result.getValue(ProcessorEnum.L2CacheSize, i))
                        .l3CacheSize((Integer) result.getValue(ProcessorEnum.L3CacheSize, i))
                        .maxClockSpeed((Integer) result.getValue(ProcessorEnum.MaxClockSpeed, i))
                        .extClock((Integer) result.getValue(ProcessorEnum.ExtClock, i))
                        .socketDesignation((String) result.getValue(ProcessorEnum.SocketDesignation, i))
                        .version((String) result.getValue(ProcessorEnum.Version, i))
                        .caption((String) result.getValue(ProcessorEnum.Caption, i))
                        .family((Integer) result.getValue(ProcessorEnum.Family, i))
                        .stepping((String) result.getValue(ProcessorEnum.Stepping, i))
                        .virtualizationFirmwareEnabled((Boolean) result.getValue(ProcessorEnum.VirtualizationFirmwareEnabled, i))
                        .processorId((String) result.getValue(ProcessorEnum.ProcessorId, i))
                        .build();

                processorList.add(processor);
            }
            return processorList;

        } finally {
            ComUtil.uninitialize();

        }
    }
}
