package io.github.eggy03.pinetree.windows.mapper.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.processor.Processor;
import io.github.eggy03.pinetree.windows.enums.processor.ProcessorProperty;
import io.github.eggy03.pinetree.windows.mapper.CommonMappingInterface;

import java.util.ArrayList;
import java.util.List;

import static io.github.eggy03.pinetree.windows.util.CastUtil.toBooleanValue;
import static io.github.eggy03.pinetree.windows.util.CastUtil.toIntegerValue;
import static io.github.eggy03.pinetree.windows.util.CastUtil.toStringValue;

/**
 * Converts raw WMI query results into {@link Processor} entities.
 * <p>
 * Methods of this class map the result provided by {@link WbemcliUtil.WmiResult} to
 * immutable {@link Processor} objects for easier consumption within the application.
 *
 * <h5>Thread Safety</h5>
 * Instances are stateless and therefore thread-safe.
 */
public class ProcessorMapper implements CommonMappingInterface<Processor, ProcessorProperty> {

    /**
     * Maps WMI result data into a list of {@link Processor} entities.
     *
     * @param result the WMI query result containing properties defined in {@link ProcessorProperty}
     * @return a list of mapped {@link Processor} instances
     */
    @Override
    public List<Processor> toEntityList (WbemcliUtil.WmiResult<ProcessorProperty> result) {

        List<Processor> processorList = new ArrayList<>();

        // Enumerate through the results
        for(int i=0; i< result.getResultCount(); i++) {
            // Build Processor Object
            Processor processor = Processor.builder()
                    .deviceId(toStringValue(result.getValue(ProcessorProperty.DeviceID, i)))
                    .name(toStringValue(result.getValue(ProcessorProperty.Name, i)))
                    .numberOfCores(toIntegerValue(result.getValue(ProcessorProperty.NumberOfCores, i)))
                    .threadCount(toIntegerValue(result.getValue(ProcessorProperty.ThreadCount, i)))
                    .numberOfLogicalProcessors(toIntegerValue(result.getValue(ProcessorProperty.NumberOfLogicalProcessors, i)))
                    .manufacturer(toStringValue(result.getValue(ProcessorProperty.Manufacturer, i)))
                    .addressWidth(toIntegerValue(result.getValue(ProcessorProperty.AddressWidth, i)))
                    .l2CacheSize(toIntegerValue(result.getValue(ProcessorProperty.L2CacheSize, i)))
                    .l3CacheSize(toIntegerValue(result.getValue(ProcessorProperty.L3CacheSize, i)))
                    .maxClockSpeed(toIntegerValue(result.getValue(ProcessorProperty.MaxClockSpeed, i)))
                    .extClock(toIntegerValue(result.getValue(ProcessorProperty.ExtClock, i)))
                    .socketDesignation(toStringValue(result.getValue(ProcessorProperty.SocketDesignation, i)))
                    .version(toStringValue(result.getValue(ProcessorProperty.Version, i)))
                    .caption(toStringValue(result.getValue(ProcessorProperty.Caption, i)))
                    .family(toIntegerValue(result.getValue(ProcessorProperty.Family, i)))
                    .stepping(toStringValue(result.getValue(ProcessorProperty.Stepping, i)))
                    .virtualizationFirmwareEnabled(toBooleanValue(result.getValue(ProcessorProperty.VirtualizationFirmwareEnabled, i)))
                    .processorId(toStringValue(result.getValue(ProcessorProperty.ProcessorId, i)))
                    .build();

            processorList.add(processor);
        }
        return processorList;
    }
}
