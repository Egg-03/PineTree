package org.pinetree.mapper;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.enums.processor.ProcessorProperty;
import org.pinetree.entity.Processor;

import java.util.ArrayList;
import java.util.List;

public class ProcessorMapper {

    public List<Processor> toEntityList (WbemcliUtil.WmiResult<ProcessorProperty> result) {

        List<Processor> processorList = new ArrayList<>();

        // Enumerate through the results
        for(int i=0; i< result.getResultCount(); i++) {
            // Build Processor Object
            Processor processor = Processor.builder()
                    .deviceId((String) result.getValue(ProcessorProperty.DeviceID, i))
                    .name((String) result.getValue(ProcessorProperty.Name, i))
                    .numberOfCores((Integer) result.getValue(ProcessorProperty.NumberOfCores, i))
                    .threadCount((Integer) result.getValue(ProcessorProperty.ThreadCount, i))
                    .numberOfLogicalProcessors((Integer) result.getValue(ProcessorProperty.NumberOfLogicalProcessors, i))
                    .manufacturer((String) result.getValue(ProcessorProperty.Manufacturer, i))
                    .addressWidth((Integer) result.getValue(ProcessorProperty.AddressWidth, i))
                    .l2CacheSize((Integer) result.getValue(ProcessorProperty.L2CacheSize, i))
                    .l3CacheSize((Integer) result.getValue(ProcessorProperty.L3CacheSize, i))
                    .maxClockSpeed((Integer) result.getValue(ProcessorProperty.MaxClockSpeed, i))
                    .extClock((Integer) result.getValue(ProcessorProperty.ExtClock, i))
                    .socketDesignation((String) result.getValue(ProcessorProperty.SocketDesignation, i))
                    .version((String) result.getValue(ProcessorProperty.Version, i))
                    .caption((String) result.getValue(ProcessorProperty.Caption, i))
                    .family((Integer) result.getValue(ProcessorProperty.Family, i))
                    .stepping((String) result.getValue(ProcessorProperty.Stepping, i))
                    .virtualizationFirmwareEnabled((Boolean) result.getValue(ProcessorProperty.VirtualizationFirmwareEnabled, i))
                    .processorId((String) result.getValue(ProcessorProperty.ProcessorId, i))
                    .build();

            processorList.add(processor);
        }
        return processorList;
    }
}
