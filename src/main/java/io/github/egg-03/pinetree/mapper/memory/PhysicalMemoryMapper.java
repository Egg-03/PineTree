package org.pinetree.mapper.memory;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.entity.memory.PhysicalMemory;
import org.pinetree.enums.memory.PhysicalMemoryProperty;

import java.util.ArrayList;
import java.util.List;

import static org.pinetree.util.CastUtil.toIntegerValue;
import static org.pinetree.util.CastUtil.toLongValue;
import static org.pinetree.util.CastUtil.toStringValue;

/**
 * Converts raw WMI query results into {@link PhysicalMemory} entities.
 * <p>
 * Methods of this class map the result provided by {@link WbemcliUtil.WmiResult} to
 * immutable {@link PhysicalMemory} objects for easier consumption within the application.
 *
 * <h3>Thread Safety</h3>
 * Instances are stateless and therefore thread-safe.
 */
public class PhysicalMemoryMapper {

    /**
     * Maps WMI result data into a list of {@link PhysicalMemory} entities.
     *
     * @param result the WMI query result containing properties defined in {@link PhysicalMemoryProperty}
     * @return a list of mapped {@link PhysicalMemory} instances
     */
    public List<PhysicalMemory> toEntityList (WbemcliUtil.WmiResult<PhysicalMemoryProperty> result) {

        List<PhysicalMemory> memoryList = new ArrayList<>();

        for (int i = 0; i < result.getResultCount(); i++) {
            PhysicalMemory memory = PhysicalMemory.builder()
                    .bankLabel(toStringValue(result.getValue(PhysicalMemoryProperty.BankLabel, i)))
                    .capacity(toLongValue(result.getValue(PhysicalMemoryProperty.Capacity, i)))
                    .configuredClockSpeed(toLongValue(result.getValue(PhysicalMemoryProperty.ConfiguredClockSpeed, i)))
                    .dataWidth(toIntegerValue(result.getValue(PhysicalMemoryProperty.DataWidth, i)))
                    .deviceLocator(toStringValue(result.getValue(PhysicalMemoryProperty.DeviceLocator, i)))
                    .formFactor(toIntegerValue(result.getValue(PhysicalMemoryProperty.FormFactor, i)))
                    .manufacturer(toStringValue(result.getValue(PhysicalMemoryProperty.Manufacturer, i)))
                    .model(toStringValue(result.getValue(PhysicalMemoryProperty.Model, i)))
                    .name(toStringValue(result.getValue(PhysicalMemoryProperty.Name, i)))
                    .otherIdentifyingInfo(toStringValue(result.getValue(PhysicalMemoryProperty.OtherIdentifyingInfo, i)))
                    .partNumber(toStringValue(result.getValue(PhysicalMemoryProperty.PartNumber, i)))
                    .serialNumber(toStringValue(result.getValue(PhysicalMemoryProperty.SerialNumber, i)))
                    .speed(toLongValue(result.getValue(PhysicalMemoryProperty.Speed, i)))
                    .tag(toStringValue(result.getValue(PhysicalMemoryProperty.Tag, i)))
                    .build();

            memoryList.add(memory);
        }

        return memoryList;
    }
}
